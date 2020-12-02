package com.example.springboot.dao;

import com.example.springboot.model.persistence.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoDaoImpl implements UserInfoDao {

    private final SqlSession sqlSession;

    @Autowired
    public UserInfoDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public User getUserById(Long id) {
        return sqlSession.selectOne("org.mybatis.example.UserMapper.selectUserById",id);
    }
}
