package com.example.springboot.config;

import com.example.springboot.model.common.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

    UserRealm userRealm;

    public ShiroConfig(UserRealm userRealm) {
        this.userRealm = userRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    /**
     * Shiro Filter,实现权限相关的拦截.
     * anno - no login can access
     * authc - require login and then access
     * user - remember me can access
     * roles - get release role can access(必须有相关角色才可访问)
     * @return 配置ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //此处需要使用LinkHashMap，用以保证加载的顺序
        LinkedHashMap<String, String> shiroFilterDirectoryMap = new LinkedHashMap<>();
        shiroFilterDirectoryMap.put("/getUser","authc");
        shiroFilterDirectoryMap.put("/login", "anon");
        shiroFilterDirectoryMap.put("/register", "anon");
        shiroFilterDirectoryMap.put("/index", "anon");
        shiroFilterDirectoryMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterDirectoryMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public HashedCredentialsMatcher matcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("SHA-256");
        matcher.setHashIterations(Constant.HASH_ITERATION_TIMES);
        matcher.setStoredCredentialsHexEncoded(false);
        matcher.setHashSalted(true);
        return matcher;
    }


}
