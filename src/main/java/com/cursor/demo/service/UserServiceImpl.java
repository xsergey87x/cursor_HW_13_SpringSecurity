package com.cursor.demo.service;

import com.cursor.demo.dto.UserDto;
import com.cursor.demo.model.UserEntity;

import com.cursor.demo.objectMapper.ObjectMapper;
import com.cursor.demo.repository.UserRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Long save(UserDto userDto) {
        UserEntity user = ObjectMapper.mapFromDtoToEntity(userDto);
        String encodedPassword = new BCryptPasswordEncoder(12).encode(user.getPassword());
        user.setPassword(encodedPassword);
        UserEntity savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public UserEntity findByUserName(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("User with username=%s not found.", username)));
    }

    @Override
    public List<UserEntity> getAllUser() {
        List<UserEntity> user = new ArrayList<>();
        var userList = userRepository.findAll();
        userList.forEach(userEntity -> user.add(userEntity));
        return user;
    }

    @Bean
    String string()
    {
        return "";
    }
}
