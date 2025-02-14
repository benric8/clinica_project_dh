package com.dh.clavecompas.controllers;

import com.dh.clavecompas.bd.clavecompas.entities.User;
import com.dh.clavecompas.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<String> getUser() {
        User   newUser = userService.findByUserCode("rbendezu");
        return ResponseEntity.ok(newUser.toString());
    }
}
