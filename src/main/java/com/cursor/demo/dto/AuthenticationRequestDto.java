package com.cursor.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequestDto {
    private String userName;
    private String password;
}
