package com.cursor.demo.service;


import com.cursor.demo.dto.UserDto;
import com.cursor.demo.model.UserEntity;

public interface UserService
{
    Long save(UserDto userDto);
    UserEntity findByUsername(String username);
}
