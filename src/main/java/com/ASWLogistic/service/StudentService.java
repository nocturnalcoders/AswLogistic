package com.ASWLogistic.service;

import com.ASWLogistic.model.User;
import com.ASWLogistic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

    private final UserRepository userRepository;

    public List<User> findByAllStudentRole() {
        return userRepository.findByAllStudentRole();
    }

}
