package com.project.cts.atinv.service.impl;

import com.project.cts.atinv.config.JwtUtil;
import com.project.cts.atinv.dto.*;
import com.project.cts.atinv.entity.Role;
import com.project.cts.atinv.entity.User;
import com.project.cts.atinv.exception.ResourceAlreadyExist;
import com.project.cts.atinv.exception.ResourceNotFoundException;
import com.project.cts.atinv.repository.RoleRepository;
import com.project.cts.atinv.repository.UserRepository;
import com.project.cts.atinv.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserRegisterResponseDTO registerUser(UserRegisterDTO userDto) {
        if(userRepository.findByEmail(userDto.getEmail()).isPresent()){
            throw new ResourceAlreadyExist("Email Already Exists");
        }
        User user=modelMapper.map(userDto,User.class);
//        User user=new User();
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setContactNo(userDto.getContactNo());
        Role role=roleRepository.findByRole(userDto.getRole()).orElseThrow(
                ()-> new ResourceNotFoundException("Role Does not exist: "+userDto.getRole(),HttpStatus.NOT_FOUND)
        );
        user.setRole(role);
        user.setCreatedBy(userDto.getEmail());
        user.setCreatedOn(LocalDateTime.now());
        user.setUpdatedBy(userDto.getEmail());
        user.setUpdatedOn(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);
        UserRegisterResponseDTO responseDTO=modelMapper.map(user, UserRegisterResponseDTO.class);
//        UserResponseDTO responseDTO=new UserResponseDTO();
//        responseDTO.setName(user.getName());
//        responseDTO.setEmail(user.getEmail());
//        responseDTO.setContactNo(user.getContactNo());
//        responseDTO.setCreatedOn(user.getCreatedOn());
        responseDTO.setRole(role.getRole());
        return responseDTO;

    }

    @Override
    public UserResponseDTO getUserById(int userId){
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User not found with id: "+userId, HttpStatus.UNAUTHORIZED)
        );
        return modelMapper.map(user,UserResponseDTO.class);
    }

    @Override
    public String updateUserById(UserUpdateDTO userUpdateDTO, int userId) {
        User existingUser=userRepository.findById(userId).orElseThrow(
                ()-> new RuntimeException("User not found by Id:"+userId)
        );
        existingUser.setName(userUpdateDTO.getName());
        existingUser.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        existingUser.setContactNo(userUpdateDTO.getContactNo());
        userRepository.save(existingUser);
        return "Updated Successfully";
    }

    @Override
    public String deleteByUserId(int userId) {
        userRepository.findById(userId).orElseThrow(
                ()-> new RuntimeException("User not found by Id:"+userId)
        );
        userRepository.deleteById(userId);
        return "Deleted Successfully";
    }

//    @Override
//    public AuthenticateResponseDTO userLogin(AuthenticateDTO authenticateDTO) {
//        User user=userRepository.findByEmail(authenticateDTO.getEmail()).orElseThrow(
//                ()-> new ResourceNotFoundException("User Not Found",HttpStatus.NOT_FOUND)
//        );
//        if(user.getEmail().equals(authenticateDTO.getEmail())
//            && passwordEncoder.matches(authenticateDTO.getPassword(),user.getPassword())
//                && user.getRole().getRole().equalsIgnoreCase(authenticateDTO.getRole())
//        ){
//            return new AuthenticateResponseDTO("jwt-token-string");
//
//        }
//        else {
//            throw new ResourceNotFoundException("Invalid Credentials",HttpStatus.UNAUTHORIZED);
//        }
//    }
    @Override
    public AuthenticateResponseDTO userLogin(AuthenticateDTO authenticateDTO) {

        User user = userRepository.findByEmail(authenticateDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found", HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(authenticateDTO.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }

        String token = jwtUtil.generateToken(user);

        return new AuthenticateResponseDTO(token);
    }


}
