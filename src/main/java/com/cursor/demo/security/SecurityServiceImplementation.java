package com.cursor.demo.security;

import com.cursor.demo.dto.AuthenticationRequestDto;
import com.cursor.demo.model.UserEntity;
import com.cursor.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImplementation implements SecurityService{
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
        UserEntity user = userService.findByUsername(authRequestDto.getUsername());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequestDto.getUsername(), authRequestDto.getPassword()));
        return jwtProvider.createToken(user.getFirstName(), user.getId());
    }
}
