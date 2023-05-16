package com.bjitacademy.securityModule.service.impl;

import com.bjitacademy.securityModule.entity.UserEntity;
import com.bjitacademy.securityModule.model.UserRequestModel;
import com.bjitacademy.securityModule.model.UserResponseModel;
import com.bjitacademy.securityModule.repository.UserRepository;
import com.bjitacademy.securityModule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }

    @Override
    public ResponseEntity<Object> register(UserRequestModel requestModel) {
        UserEntity userEntity = UserEntity.builder()
                .email(requestModel.getEmail())
                .userName(requestModel.getUserName())
                .firstName(requestModel.getFirstName())
                .lastName(requestModel.getLastName())
                .password(requestModel.getPassword())
                .build();
        UserEntity savedUserEntity = userRepository.save(userEntity);

        UserResponseModel userResponseModel=UserResponseModel.builder()
                .userName(savedUserEntity.getUserName())
                .email(savedUserEntity.getEmail())
                .firstName(savedUserEntity.getFirstName())
                .build();
        return new ResponseEntity<>(userResponseModel, HttpStatus.CREATED);
    }
    @Override
    public ResponseEntity<Object> getAll() {
        List<UserEntity> userList=userRepository.findAll();
        List<UserResponseModel> userResponseModelList=new ArrayList<>();
        for (UserEntity u:userList){
            UserResponseModel userResponseModel=UserResponseModel.builder()
                    .userName(u.getUserName())
                    .email(u.getEmail())
                    .firstName(u.getFirstName())
                    .build();
            userResponseModelList.add(userResponseModel);
        }
        return new ResponseEntity<>(userResponseModelList,HttpStatus.ACCEPTED);
    }
}
