package com.example.api.servicios;

import com.example.api.model.Title_Edu;
import com.example.api.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    private EducationRepository education;


    public List<Title_Edu> getAll() {
        return this.education.findAll();
    }

    public void delete(Long id) {
        this.education.deleteById(id);
    }

    public Optional<Title_Edu> getById(Long id) {
        return this.education.findById(id);
    }

    public boolean save(Title_Edu t) {
        this.education.saveAndFlush(t);
        return true;
    }
}
