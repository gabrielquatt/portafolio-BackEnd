package com.example.api.repository;

import com.example.api.model.TitleEducation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<TitleEducation, Long> {
}
