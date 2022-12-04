package com.cursor.demo.controller;

import com.cursor.demo.dto.AuthenticationRequestDto;
import com.cursor.demo.model.UserEntity;
import com.cursor.demo.security.JwtProvider;
import com.cursor.demo.security.SecurityService;
import com.cursor.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class SignInController {
    private final SecurityService securityService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtTokenProvider;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody AuthenticationRequestDto authRequestDto) {
        try {

            String userName = authRequestDto.getUserName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, authRequestDto.getPassword()));
            UserEntity user = userService.findByUserName(userName);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + userName + " not found");
            }

            String token = securityService.getAuthentication(authRequestDto);
            Map<Object, Object> response = new HashMap<>();
            response.put("userName", userName);
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        return "success";
    }

}
