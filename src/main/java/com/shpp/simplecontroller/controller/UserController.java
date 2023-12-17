package com.shpp.simplecontroller.controller;

import com.shpp.simplecontroller.dto.UserDTO;
import com.shpp.simplecontroller.entity.UserEntity;
import com.shpp.simplecontroller.exception.ErrorMessage;
import com.shpp.simplecontroller.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User operations", description = "Realizes crud operations.")
@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    public IUserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public UserController(IUserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Operation(
            summary = "Retrieve user by id",
            description = "Get User object by specifying its id. The response is UserDTO object with id, name and ipn.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User Not Found", content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO userDTO = modelMapper.map(userService.getUserById(id), UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @Operation(
            summary = "Delete user by id",
            description = "Delete User object by specifying its id. The response is 200 if user was deleted and 404 if not.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User Not Found", content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema())})})
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    @Operation(
            summary = "Create user",
            description = "Creates User object and add it to database. The response is created UserDTO object with id, name and ipn.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema())})})
    @PostMapping
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        userDTO = (UserDTO) result.getTarget();
        if (userDTO.getName().length() < 3 || userDTO.getName().length() > 25) {
            userDTO.setName("username");
        }
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        UserDTO createdUser = modelMapper.map(userService.addUser(userEntity), UserDTO.class);
        return ResponseEntity.ok(createdUser);
    }

    @Operation(
            summary = "Retrieve all users",
            description = "Get List of all User objects. The response is List of UserDTO objects with id, name and ipn.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "No Content", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema())})})
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        List<UserDTO> users = userService.getAllUsers().stream().map(userEntity -> modelMapper
                .map(userEntity, UserDTO.class)).toList();
        if (users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(users);
    }

    @Operation(
            summary = "Update user",
            description = "Update User object information in database. The response is updated UserDTO object with id, name and ipn.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(schema = @Schema(implementation = UserDTO.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User Not Found", content = {@Content(schema = @Schema(implementation = ErrorMessage.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema())})})
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        UserDTO updatedUser = modelMapper.map(userService.updateUserInfo(id, userEntity), UserDTO.class);
        return ResponseEntity.ok(updatedUser);
    }


}
