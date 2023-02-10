package com.ASWLogistic.repository;

import com.ASWLogistic.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<User, Long> {


}
