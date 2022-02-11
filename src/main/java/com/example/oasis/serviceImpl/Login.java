package com.example.oasis.serviceImpl;

import com.example.oasis.dao.UserMapper;
import com.example.oasis.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Login {
    @Autowired
    private UserMapper userMapper;

    public boolean hasUser(String username,String password){

        User user=userMapper.getUser(username);

        if(password.equals(user.getPassword())){

            return true;
        }

        return false;
    }
}
