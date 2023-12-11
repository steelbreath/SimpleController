package com.shpp.simplecontroller.service;

import com.shpp.simplecontroller.entity.UserEntity;
import com.shpp.simplecontroller.exception.UserNotFoundException;
import com.shpp.simplecontroller.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;


class UserServiceTest {


    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void getAllUsersTest() {
        List<UserEntity> userEntities = List.of(new UserEntity(1L, "Nana", 11253453L),
                new UserEntity(2L, "Root", 1273694L));
        Mockito.when(userRepository.findAll()).thenReturn(userEntities);
        Assertions.assertEquals(userEntities, userService.getAllUsers());
    }

    @Test
    void addUserTest() {
        UserEntity userEntity = new UserEntity(1L, "Pasha", 1234567899L);
        ArgumentCaptor<UserEntity> argCapt = ArgumentCaptor.forClass(UserEntity.class);
        userService.addUser(userEntity);
        Mockito.verify(userRepository, Mockito.times(1)).save(argCapt.capture());
        Assertions.assertEquals(userEntity, argCapt.getValue());

    }

    @Test
    void updateUserInfoTest() {
        Long id = 1L;
        ArgumentCaptor<UserEntity> argCapt = ArgumentCaptor.forClass(UserEntity.class);
        UserEntity userEntity = new UserEntity(id, "Nana", 1234567899L);
        UserEntity updatedUser = new UserEntity(id, "Pasha", 1234567899L);
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        userService.updateUserInfo(updatedUser);
        Mockito.verify(userRepository, Mockito.times(1)).save(argCapt.capture());
        Assertions.assertEquals(updatedUser, argCapt.getValue());
    }

    @Test
    void updateUserInfoNegativeTest() {
        UserEntity updatedUser = new UserEntity(6L, "Pasha", 1234567899L);
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.updateUserInfo(updatedUser));
    }

    @Test
    void deleteUserTest() {
        Long id = 1L;
        ArgumentCaptor<Long> argCapt = ArgumentCaptor.forClass(Long.class);
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(new UserEntity()));
        userService.deleteUser(id);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(argCapt.capture());
        Assertions.assertEquals(id, argCapt.getValue());
    }

    @Test
    void deleteUserNegativeTest() {
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.deleteUser(6L));
    }

    @Test
    void getUserByIdTest() {
        Long id = 1L;
        ArgumentCaptor<Long> argCapt = ArgumentCaptor.forClass(Long.class);
        Mockito.when(userRepository.findById(id)).thenReturn(Optional.of(new UserEntity()));
        userService.getUserById(id);
        Mockito.verify(userRepository, Mockito.times(1)).findById(argCapt.capture());
        Assertions.assertEquals(id, argCapt.getValue());
    }

    @Test
    void getUserByIdNegativeTest() {
        Long id = 6L;
        ArgumentCaptor<Long> argCapt = ArgumentCaptor.forClass(Long.class);
        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserById(id));
        Mockito.verify(userRepository, Mockito.times(1)).findById(argCapt.capture());
        Assertions.assertEquals(id, argCapt.getValue());

    }
}