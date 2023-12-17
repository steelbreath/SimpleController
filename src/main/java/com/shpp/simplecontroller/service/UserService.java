package com.shpp.simplecontroller.service;

import com.shpp.simplecontroller.entity.UserEntity;
import com.shpp.simplecontroller.exception.UserNotFoundException;
import com.shpp.simplecontroller.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity addUser(@Valid UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUserInfo(Long id, @Valid UserEntity userEntity) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("There no user with such id.");
        }
        return userRepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        if(userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException("There no user with such id.");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id)
                        .orElseThrow(() -> new UserNotFoundException("There no user with such id."));
    }
}
