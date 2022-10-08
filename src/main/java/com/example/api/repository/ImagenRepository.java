package com.example.api.repository;

import com.example.api.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer> {
    List<Imagen> findByOrderById();


    @Query(value = "SELECT i FROM Imagen i WHERE i.name = :name")
    Optional<Imagen> findByName(String name);
}
