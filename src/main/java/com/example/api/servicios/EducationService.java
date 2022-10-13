package com.example.api.servicios;

import com.example.api.model.Education;
import com.example.api.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    private EducationRepository education;


    public List<Education> getAll() {
        return this.education.findAll();
    }

    public void delete(Long id) {
        this.education.deleteById(id);
    }

    public Optional<Education> getById(Long id) {
        return this.education.findById(id);
    }

    public boolean save(Education t) {
        this.education.saveAndFlush(t);
        return true;
    }
}
