package com.ASWLogistic.repository;

import com.ASWLogistic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);

    @Query(nativeQuery = true,value = "select * from user inner join users_roles on user.id = users_roles.user_id inner join role on users_roles.role_id = role.id where role.id = 2")
    List<User> findByAllStudentRole();
    @Query(nativeQuery = true,value = "select * from user inner join users_roles on user.id = users_roles.user_id inner join role on users_roles.role_id = role.id where role.id = 3")
    List<User> findByAllTeacherRole();

}
