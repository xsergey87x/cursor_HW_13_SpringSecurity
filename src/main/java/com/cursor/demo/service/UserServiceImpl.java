package com.cursor.demo.service;

import com.cursor.demo.dto.UserDto;
import com.cursor.demo.model.UserEntity;

import com.cursor.demo.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Long save(UserDto userDto) {
        return null;
    }

    @Override
    public UserEntity findByUsername(String username) {
       return null;
    }


    @Bean
    String string()
    {
        return "";
    }
}
