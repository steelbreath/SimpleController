package com.shpp.simplecontroller.service;

import com.shpp.simplecontroller.entity.UserEntity;

import java.util.List;

public interface IUserService {

    List<UserEntity> getAllUsers();

    UserEntity addUser(UserEntity userEntity);

    UserEntity updateUserInfo(Long id, UserEntity userEntity);

    void deleteUser(Long id);

    UserEntity getUserById(Long id);

}
