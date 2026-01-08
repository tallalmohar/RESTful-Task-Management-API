package com.tallalmohar.taskmanager.repository;

import com.tallalmohar.taskmanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
