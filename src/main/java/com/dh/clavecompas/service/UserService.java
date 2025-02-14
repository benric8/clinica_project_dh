package com.dh.clavecompas.service;

import com.dh.clavecompas.bd.clavecompas.entities.User;
import com.dh.clavecompas.bd.clavecompas.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUserCode(String userCode) {
        return userRepository.findByUserCode(userCode);
    }
}
