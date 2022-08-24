package com.example.api.servicios;

import com.example.api.model.TitleEducation;
import com.example.api.repository.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationService {

    @Autowired
    private EducationRepository education;


    public List<TitleEducation> getAll() {
        return this.education.findAll();
    }

    public void delete(Long id) {
        this.education.deleteById(id);
    }

    public Optional<TitleEducation> getById(Long id) {
        return this.education.findById(id);
    }

    public boolean save(TitleEducation t) {
        this.education.saveAndFlush(t);
        return true;
    }
}
