package com.example.springboot.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

    UserRealm userRealm;

    @Autowired
    public ShiroConfig(UserRealm userRealm) {
        this.userRealm = userRealm;
    }

    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }

    /**
     * Shiro Filter,实现权限相关的拦截
     *
     *
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        LinkedHashMap<String,String> shiroFilterDirectoryMap = new LinkedHashMap<>();//此处需要使用LinkHashMap，用以保证加载的顺序
//        shiroFilterDirectoryMap.put("/getUser","authc");
        shiroFilterDirectoryMap.put("/login","anon");
        shiroFilterDirectoryMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFilterDirectoryMap);
        return shiroFilterFactoryBean;
    }
}
