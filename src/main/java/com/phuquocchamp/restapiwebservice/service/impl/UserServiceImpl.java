package com.phuquocchamp.restapiwebservice.service.impl;

import com.phuquocchamp.restapiwebservice.dto.UserDto;
import com.phuquocchamp.restapiwebservice.entity.User;
import com.phuquocchamp.restapiwebservice.exception.EmailAlreadyExitsException;
import com.phuquocchamp.restapiwebservice.exception.ResourceNotFoundException;
import com.phuquocchamp.restapiwebservice.mapper.UserMapper;
import com.phuquocchamp.restapiwebservice.repository.UserRepository;
import com.phuquocchamp.restapiwebservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        // Find by Email.
        Optional<User> checkExitsEmail = userRepository.findByEmail(userDto.getEmail());
        if (checkExitsEmail.isPresent()) {
            throw new EmailAlreadyExitsException("Email already exits!");
        } else {
            // Convert UserDto into User
            User user = modelMapper.map(userDto, User.class);
            // save user to database;
            User savedUser = userRepository.save(user);
            // Convert savedUser to UserDto and return to UserDto
            return modelMapper.map(savedUser, UserDto.class);
        }
    }

    @Override
    public UserDto getUserById(Long userId) {
        // orElseThrow => Nếu không tìm thấy user với id trong database thì ném ra ngoại lệ.
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(UserMapper.mapToUserDto(user));
        }
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User exitedUser = userRepository.getById(userDto.getId());
        exitedUser.setFirstName(userDto.getFirstName());
        exitedUser.setLastName(userDto.getLastName());
        exitedUser.setEmail(userDto.getLastName());
        User savedUser = userRepository.save(exitedUser);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
