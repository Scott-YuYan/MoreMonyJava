package com.example.springboot.dao;

import com.example.springboot.model.persistence.User;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserInfoDaoImpl implements UserInfoDao {

    private final SqlSession sqlSession;

    @Override
    public User getUserById(Long id) {
        return sqlSession.selectOne("org.mybatis.example.UserMapper.selectUserById", id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return sqlSession.selectOne(
                "org.mybatis.example.UserMapper.selectUserByUserName", userName);
    }
}
