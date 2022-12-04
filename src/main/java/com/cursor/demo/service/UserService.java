package com.cursor.demo.service;


import com.cursor.demo.dto.UserDto;
import com.cursor.demo.model.UserEntity;

import java.util.List;

public interface UserService
{
    Long save(UserDto userDto);
    UserEntity findByUserName(String username);
    List<UserEntity> getAllUser();
}
