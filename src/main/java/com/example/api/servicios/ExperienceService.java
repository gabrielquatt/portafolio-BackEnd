package com.example.api.servicios;

import com.example.api.model.Experience;
import com.example.api.repository.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceService {

    @Autowired
    private ExperienceRepository work_Experience;

    public List<Experience> getAll() {
        return this.work_Experience.findAll();
    }

    public void delete(Long id) {
        this.work_Experience.deleteById(id);
    }

    public Optional<Experience> getById(Long id) {
        return  this.work_Experience.findById(id);
    }

    public boolean save(Experience w) {
        this.work_Experience.saveAndFlush(w);
        return true;
    }

}
