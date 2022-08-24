package com.example.api.repository;

import com.example.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

    //Querys personalizadas

}
