package com.shpp.simplecontroller.controller;

import com.shpp.simplecontroller.entity.UserEntity;
import com.shpp.simplecontroller.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "User operations",description = "Realizes crud operations.")
@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/hello")
    public String printHello(){
        return "Hello World";
    }

    @Operation(
            summary = "Retrieve a Tutorial by Id",
            description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "", content = { @Content(schema = @Schema(implementation = UserEntity.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", description = "", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        return ResponseEntity.of(user);
    }


}

//http://localhost:8080/
//http://localhost:8080/swagger-ui/index.html
//http://localhost:8080/h2-console
