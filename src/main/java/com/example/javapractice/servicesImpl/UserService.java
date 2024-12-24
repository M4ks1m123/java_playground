package com.example.javapractice.servicesImpl;

import com.example.javapractice.models.RoleEntity;
import com.example.javapractice.models.UserEntity;
import com.example.javapractice.repositories.RoleRepository;
import com.example.javapractice.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null){
            throw new UsernameNotFoundException("User not found");
        }

        return userEntity;
    }

    public UserEntity findUserById(Long userId){
        Optional<UserEntity> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new UserEntity());
    }

    public List<UserEntity> allUsers(){
        return userRepository.findAll();
    }

    public boolean saveUser(UserEntity userEntity) {
        UserEntity userFromDb = userRepository.findByUsername(userEntity.getUsername());

        if( userFromDb!=null)
            return false;

        userEntity.setRoles(Collections.singleton(new RoleEntity(1L, "ROLE_USER")));
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
        return true;
    }

    public boolean deleteUser(Long userId){
        if (userRepository.findById(userId).isPresent()){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<UserEntity> usergtList(Long idMin){
        return em.createQuery("select u from UserEntity u where u.id > :paramId", UserEntity.class)
                .setParameter("paramId", idMin).getResultList();
    }
}
