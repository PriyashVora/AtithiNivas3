package com.project.cts.atinv.controller;

import com.project.cts.atinv.dto.*;
import com.project.cts.atinv.entity.User;
import com.project.cts.atinv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDTO> addUser(@RequestBody UserRegisterDTO userDto){
        UserRegisterResponseDTO newUser=userService.registerUser(userDto);
        return new ResponseEntity<UserRegisterResponseDTO>(newUser, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<UserResponseDTO> getUserById(@RequestHeader("X-User-Id") String headerUserId,
            @RequestHeader("X-Role") String role){
    	int userId = Integer.parseInt(headerUserId);
        UserResponseDTO getUser=userService.getUserById(userId);
        return new ResponseEntity<UserResponseDTO>(getUser, HttpStatus.OK);

    }
    @PutMapping
    public ResponseEntity<String> updateUserById(
            @RequestHeader("X-User-Id") String headerUserId,
            @RequestHeader("X-Role") String role,
            @RequestBody UserUpdateDTO userUpdateDTO) {

    	int userId = Integer.parseInt(headerUserId);

        String updatedUser = userService.updateUserById(userUpdateDTO, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
//
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> deleteByUserId(
//            @RequestHeader("X-User-Id") String headerUserId) {
//
//		  int userId = Integer.parseInt(headerUserId);
//
//        String deletedUser = userService.deleteByUserId(userId);
//        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
//    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticateResponseDTO> userLogin(@RequestBody AuthenticateDTO authenticateDTO){
        AuthenticateResponseDTO responseDTO=userService.userLogin(authenticateDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);

    }
//    @PatchMapping("/{userId}")
//    public ResponseEntity<String> updateUserById(@RequestHeader("X-Role") String role, @RequestBody UserUpdateDTO userUpdateDTO,@PathVariable int userId){
//        String updatedUser=userService.updateUserById(userUpdateDTO ,userId);
//        return new ResponseEntity<String>(updatedUser,HttpStatus.OK);
//    }

}
