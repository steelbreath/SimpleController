package com.shpp.simplecontroller.controller;

import com.shpp.simplecontroller.dto.UserDTO;
import com.shpp.simplecontroller.entity.UserEntity;
import com.shpp.simplecontroller.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;

class UserControllerTest {

    private ModelMapper modelMapper;
    private IUserService userService;
    private UserController userController;

    @BeforeEach
    void setUp(){
        modelMapper = Mockito.mock(ModelMapper.class);
        userService = Mockito.mock(IUserService.class);
        userController = new UserController(userService,modelMapper);
    }

    @Test
    void getUserTest() {
        Mockito.when(userService.getUserById(anyLong())).thenReturn(new UserEntity());
        userController.getUser(anyLong());
        Mockito.verify(modelMapper,Mockito.times(1))
                .map(any(UserEntity.class), eq(UserDTO.class));
    }

    @Test
    void addUserTest() {
        BindingResult result = Mockito.mock(BindingResult.class);
        Mockito.when(userService.addUser(any(UserEntity.class))).thenReturn(new UserEntity());
        userController.addUser(new UserDTO("q",1234567899L),result);
        Mockito.verify(modelMapper,Mockito.times(1))
                .map(any(), eq(UserDTO.class));
        Mockito.verify(modelMapper,Mockito.times(1))
                .map(any(), eq(UserEntity.class));
    }

    @Test
    void getAllTest() {
        List<UserEntity> userEntities = List.of(new UserEntity(1L,"Nana",11253453L),
                new UserEntity(2L,"Root", 1273694L));
        Mockito.when(userService.getAllUsers()).thenReturn(userEntities);
        userController.getAll();
        Mockito.verify(modelMapper,Mockito.times(userEntities.size()))
                .map(any(UserEntity.class), eq(UserDTO.class));
    }

    @Test
    void updateUserTest() {
        BindingResult result = Mockito.mock(BindingResult.class);
        Mockito.when(userService.updateUserInfo(anyLong(),any(UserEntity.class))).thenReturn(new UserEntity());
        userController.updateUser(1L,new UserDTO("q",1234567899L), result);
        Mockito.verify(modelMapper,Mockito.times(1))
                .map(any(), eq(UserDTO.class));
        Mockito.verify(modelMapper,Mockito.times(1))
                .map(any(), eq(UserEntity.class));
    }
}