package com.example.community.mapper;

import com.example.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    public void insertUser(User user);

    User findByToken(@Param("token") String token);
}
