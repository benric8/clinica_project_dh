package com.dh.clavecompas.bd.clavecompas.repositories;

import com.dh.clavecompas.bd.clavecompas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserCode(String userCode);
}
