package com.project.cts.atinv.service;

import com.project.cts.atinv.dto.*;
import com.project.cts.atinv.entity.User;

public interface UserService {
    UserRegisterResponseDTO registerUser(UserRegisterDTO userDto);

    UserResponseDTO getUserById(int userId);

    String updateUserById(UserUpdateDTO userUpdateDTO, int userId);

    String deleteByUserId(int userId);

    AuthenticateResponseDTO userLogin(AuthenticateDTO authenticateDTO);
}
