package com.fullstack.expense_tracker.repository;

import com.fullstack.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email); // Used to find user by email
}
