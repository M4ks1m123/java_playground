package com.example.javapractice.repositories;

import com.example.javapractice.models.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
