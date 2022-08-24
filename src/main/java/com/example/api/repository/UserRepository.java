package com.example.api.repository;

import com.example.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u FROM User u WHERE u.name = :name")
    User getByEmail(String name);

    @Query(value = "SELECT u FROM User u WHERE u.name = :name AND u.pass = :pass")
    User comparePass(String name, String pass);

}
