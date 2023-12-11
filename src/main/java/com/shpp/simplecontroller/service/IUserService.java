package com.shpp.simplecontroller.service;

import com.shpp.simplecontroller.entity.UserEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {

    List<UserEntity> getAllUsers();

    UserEntity addUser(UserEntity userEntity);

    UserEntity updateUserInfo(UserEntity userEntity);

    void deleteUser(Long id);

    UserEntity getUserById(Long id);

}
