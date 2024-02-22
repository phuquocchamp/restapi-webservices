package com.phuquocchamp.restapiwebservice.service;

import com.phuquocchamp.restapiwebservice.dto.UserDto;
import com.phuquocchamp.restapiwebservice.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);
    void deleteUser(Long userId);
}
