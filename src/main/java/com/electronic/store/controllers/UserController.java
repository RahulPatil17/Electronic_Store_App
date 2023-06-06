package com.electronic.store.controllers;

import com.electronic.store.dtos.ApiResponseMessage;
import com.electronic.store.dtos.UserDto;
import com.electronic.store.helper.AppConstants;
import com.electronic.store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author Rahul
     * @apiNote This Api is used for create User
     * @param userDto
     * @return
     */

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Initiated request for create User details");
        UserDto userDto1 = userService.createUser(userDto);
        log.info("Completed request for create User details");
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    /**
     * @author Rahul
     * @apiNote This Api is used to update User
     * @param userDto
     * @param userId
     * @return
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid@RequestBody UserDto userDto, @PathVariable("userId") String userId) {
        log.info("Initiated request for update User details with userId:{}", userId);
        UserDto updateUser = userService.updateUser(userDto, userId);
        log.info("Completed request for update User details with userId:{}", userId);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    /**
     * @author Rahul
     * @apiNote This Api is used to Get All Users
     * @return
     */

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Initiated request for get All User details ");
        List<UserDto> allUsers = userService.getAlluser();
        log.info("Completed request for get All User details ");
        return new ResponseEntity<List<UserDto>>(allUsers, HttpStatus.OK);
    }

    /**
     * @author Rahul
     * @apiNote This Api is used to Delete User with userId
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable("userId") String userId) {
        log.info("Initiated request for delete user  details with userId:{}", userId);
        userService.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage.builder().message(AppConstants.USER_DELETED).success(true).status(HttpStatus.OK).build();
        log.info("Completed request for delete user  details with userId:{}", userId);
        return new ResponseEntity<ApiResponseMessage>(message, HttpStatus.OK);
    }
    /**
     * @author Rahul
     * @apiNote This Api is used to Get Single User With Id
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> singleUserById(@PathVariable String userId) {
        log.info("Initiated request for get user details with userId:{}", userId);
        UserDto userById = userService.singleuserById(userId);
        log.info("Completed request for get user details with userId:{}", userId);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }
    /**
     * @author Rahul
     * @apiNote This Api is used to Get User By Email
     * @param email
     * @return
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        log.info("Initiated request for get user details with email:{}", email);
        UserDto userByEmail = userService.getuserByEmail(email);
        log.info("Completed request for get user details with email:{}", email);
        return new ResponseEntity<>(userByEmail, HttpStatus.OK);
    }

    /**
     * @author Rahul
     * @apiNote This Api is used to Search User With Keyword
     * @param keyword
     * @return
     */
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable("keyword") String keyword) {
        log.info("Initiated request for search user details with keyword:{}", keyword);
        List<UserDto> user = this.userService.searchuser(keyword);
        log.info("Completed request for search user details with keyword:{}", keyword);
        return new ResponseEntity<List<UserDto>>(user, HttpStatus.OK);
    }
}

