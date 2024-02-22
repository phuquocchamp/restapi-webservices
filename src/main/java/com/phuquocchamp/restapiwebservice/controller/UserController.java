package com.phuquocchamp.restapiwebservice.controller;

import com.phuquocchamp.restapiwebservice.dto.UserDto;
import com.phuquocchamp.restapiwebservice.entity.User;
import com.phuquocchamp.restapiwebservice.exception.ErrorDetail;
import com.phuquocchamp.restapiwebservice.exception.ResourceNotFoundException;
import com.phuquocchamp.restapiwebservice.mapper.UserMapper;
import com.phuquocchamp.restapiwebservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {
    private UserService userService;
    @PostMapping("create-user")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
    @GetMapping("get-user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto gotUser = userService.getUserById(userId);
        return new ResponseEntity<>(gotUser, HttpStatus.OK);
    }
    @GetMapping("get-all-users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> userDtos = userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
    @PutMapping("update-user")
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto){
        UserDto updatedUser = userService.updateUser(userDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>("Deleted user successfully", HttpStatus.OK);
    }


    // The @ExceptionHandler is an annotation used to handle the specific exceptions and sending
    // the custom responses to the client.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {
        ErrorDetail errorDetail = new ErrorDetail(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "USER_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

}
