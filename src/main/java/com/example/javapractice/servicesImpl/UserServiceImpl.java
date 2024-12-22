package com.example.javapractice.servicesImpl;

import com.example.javapractice.models.UserEntity;
import com.example.javapractice.repositories.UserRepository;
import com.example.javapractice.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
