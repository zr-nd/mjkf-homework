package com.example.oasis.dao;

import com.example.oasis.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    User getUser(@Param("username")String username);
}
