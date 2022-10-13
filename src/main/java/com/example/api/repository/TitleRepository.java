package com.example.api.repository;

import com.example.api.model.InformationEdu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<InformationEdu, Long> {
}
