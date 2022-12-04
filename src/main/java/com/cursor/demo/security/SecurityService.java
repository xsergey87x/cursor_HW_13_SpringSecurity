package com.cursor.demo.security;


import com.cursor.demo.dto.AuthenticationRequestDto;

public interface SecurityService {
    String getAuthentication(AuthenticationRequestDto authRequestDto);
}
