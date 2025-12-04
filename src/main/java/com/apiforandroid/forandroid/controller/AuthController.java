package com.apiforandroid.forandroid.controller;

import com.apiforandroid.forandroid.dto.LoginRequestDTO;
import com.apiforandroid.forandroid.entity.UserEntity;
import com.apiforandroid.forandroid.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(value = "*")
public class AuthController {

    private UserService userService;
    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity user){
        UserEntity userRegistred =  userService.registerUser(user);

        logger.info("Usuario: {} guardado exitosamente en base de datos con ID: {}.", user.getEmail(), userRegistred.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(userRegistred);
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(
            @RequestBody LoginRequestDTO loginRequestDTO){

        UserEntity userEncontred = userService.login(loginRequestDTO.email(), loginRequestDTO.password());

        logger.info("Usuario: {} logueado exitosamente.", loginRequestDTO.email());

        return ResponseEntity.ok(userEncontred);

    }
}
