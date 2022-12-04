package com.cursor.demo.security;

import com.cursor.demo.dto.AuthenticationRequestDto;
import com.cursor.demo.model.UserEntity;
import com.cursor.demo.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImplementation implements SecurityService {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public SecurityServiceImplementation(UserService userService, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String getAuthentication(AuthenticationRequestDto authRequestDto) {

        UserEntity user = userService.findByUserName(authRequestDto.getUserName());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getUserName(), authRequestDto.getPassword()));
        return jwtProvider.createToken(user.getUserName(), user.getId());
    }
}
