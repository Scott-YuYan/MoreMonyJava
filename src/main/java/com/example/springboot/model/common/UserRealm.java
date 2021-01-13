package com.example.springboot.model.common;

import com.example.springboot.manager.UserInfoManager;
import lombok.val;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 定义UserRealm来验证用户的身份信息
 * Realm作为Shiro和加密数据之间的桥梁
 */
@Component
public class UserRealm extends AuthorizingRealm {

    private final UserInfoManager userInfoManager;


    @Autowired
    public UserRealm(UserInfoManager userInfoManager,
                     @Lazy HashedCredentialsMatcher matcher) {
        super(matcher);
        this.userInfoManager = userInfoManager;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        String userName = (String) token.getPrincipal();

        val user = userInfoManager.getUserByUserName(userName);
        if (userName.isEmpty() || user == null) {
            throw new UnknownAccountException(String.format("Username %s not found", userName));
        }
        return new SimpleAuthenticationInfo(userName, user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), this.getName());
    }
}
