package com.example.api.servicios;

import com.example.api.model.EducationTitle;
import com.example.api.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    private EducationRepository education;


    public List<EducationTitle> getAll() {
        return this.education.findAll();
    }

    public void delete(Long id) {
        this.education.deleteById(id);
    }

    public Optional<EducationTitle> getById(Long id) {
        return this.education.findById(id);
    }

    public boolean save(EducationTitle t) {
        this.education.saveAndFlush(t);
        return true;
    }
}
