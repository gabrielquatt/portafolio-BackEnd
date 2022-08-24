package com.example.api.servicios;

import com.example.api.model.Language;
import com.example.api.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    @Autowired
    private LanguageRepository language;

    public List<Language> getAll() {
        return this.language.findAll();
    }

    public void delete(Long id) {
        this.language.deleteById(id);
    }

    public Optional<Language> getById(Long id) {
        return  this.language.findById(id);
    }

    public boolean save(Language l) {
        this.language.saveAndFlush(l);
        return true;
    }
}
