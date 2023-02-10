package com.ASWLogistic.service;

import com.ASWLogistic.dto.UserRegistrationDto;
import com.ASWLogistic.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

interface IUserService extends UserDetailsService {

    User findByEmail(String email);


    User save_student(UserRegistrationDto registration);

    User save_teacher(UserRegistrationDto registration);
}