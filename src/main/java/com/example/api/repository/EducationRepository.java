package com.example.api.repository;

import com.example.api.model.Title_Edu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Title_Edu, Long> {
}
