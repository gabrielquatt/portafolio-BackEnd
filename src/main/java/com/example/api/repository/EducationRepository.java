package com.example.api.repository;

import com.example.api.model.EducationTitle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<EducationTitle, Long> {
}
