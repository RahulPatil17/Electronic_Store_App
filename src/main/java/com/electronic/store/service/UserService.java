package com.electronic.store.service;

import com.electronic.store.dtos.UserDto;

import java.util.List;

public interface UserService {

    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto, String userId);

    //delete
    void deleteUser(String userId);

    //get All users
    List<UserDto> getAlluser();

    //get single user by Id
    UserDto singleuserById(String userId);

    //get user By email

    UserDto getuserByEmail(String useremail);

    //search user
    List<UserDto> searchuser(String keyword);


}
