package com.shpp.simplecontroller.service;

import com.shpp.simplecontroller.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Override
    public List<UserEntity> getAllUsers() {
        return null;
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity updateUserInfo(Long id, UserEntity userEntity) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public UserEntity getUserById(Long id) {
        return null;
    }
}
