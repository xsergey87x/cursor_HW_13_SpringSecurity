package com.cursor.demo.controller;

import com.cursor.demo.dto.UserDto;
import com.cursor.demo.model.UserEntity;
import com.cursor.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/users/")
public class UserRestController {

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<UserEntity>> getAllUser() {
        List<UserEntity> user = userService.getAllUser();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
