package com.example.api.repository;

import com.example.api.model.Titles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitlesRepository extends JpaRepository<Titles,Long> {
}
