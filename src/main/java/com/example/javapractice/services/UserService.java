package com.example.javapractice.services;

import com.example.javapractice.models.UserEntity;

import java.util.Optional;

public interface UserService {
    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findUserById(Long id);
}
