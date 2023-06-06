package com.electronic.store.service.impl;

import com.electronic.store.dtos.UserDto;
import com.electronic.store.entities.User;
import com.electronic.store.helper.AppConstants;
import com.electronic.store.repository.UserRepository;
import com.electronic.store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
     @Autowired
     ModelMapper modelMapper = new ModelMapper();


    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Initiated Dao call for create user");
        // generate Unique id in string format
        String userId = UUID.randomUUID().toString();
        // dto->entity
        User user = DtoToEntity(userDto);
        User saveduser = userRepository.save(user);
        //entity->dto
        UserDto newDto = entityToDto(saveduser);
        log.info("Completed Dao call for create user");
        return newDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        log.info("Initiated Dao call for update User with UserId:{}",userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(AppConstants.USER_NOT_FOUND + userId));
        user.setName(userDto.getName());
        //email update
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());
        //save Data
        User updateduser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updateduser);
        log.info("completed Dao call for update User with UserId:{}",userId);
        return updatedDto;
    }

    @Override
    public void deleteUser(String userId) {
        log.info("Initiated Dao call for delete User with UserId:{}",userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(AppConstants.USER_NOT_FOUND + userId));
        //deleteuser
        userRepository.delete(user);
        log.info("completed Dao call for delete User with UserId:{}",userId);

    }

    @Override
    public List<UserDto> getAlluser() {
        log.info("Initiate dao call for get all user details");
        List<User> users = userRepository.findAll();
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        log.info("Completed dao call for get all user details");
        return dtoList;
    }

    @Override
    public UserDto singleuserById(String userId) {
        log.info("Initiating dao call for get the user details with user:{}", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException(AppConstants.USER_NOT_FOUND + userId));
        log.info("Completed dao call for get the user details with userId:{}", userId);
        return entityToDto(user);
    }



    @Override
    public UserDto getuserByEmail(String email) {
        log.info("Initiating dao call for get the user details with email:{}", email);
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(AppConstants.USER_NOT_FOUND + email));
        log.info("Completed dao call for get the user details with email:{}", email);
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchuser(String keyword) {
        log.info("Initiating dao call for search the user details with keweord:{}", keyword);
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        log.info("completed dao call for search the user details with keweord:{}", keyword);
        return dtoList;
    }

    private UserDto entityToDto(User saveduser) {
//        UserDto userDto = UserDto.builder()
//                .userId(saveduser.getUserId())
//                .name(saveduser.getName())
//                .email(saveduser.getEmail())
//                .password(saveduser.getPassword())
//                .about(saveduser.getAbout())
//                .gender(saveduser.getGender())
//                .imageName(saveduser.getImageName()).build();

        return modelMapper.map(saveduser, UserDto.class);
    }

    private User DtoToEntity(UserDto userDto) {
//        User user = User.builder()
//                .userId(userDto.getuserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName()).build();
        return modelMapper.map(userDto, User.class);
    }
}
