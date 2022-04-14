package com.example.mjkf.dao;

import com.example.mjkf.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    User getUser(@Param("username")String username);
}
