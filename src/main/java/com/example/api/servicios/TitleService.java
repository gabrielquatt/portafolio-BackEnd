package com.example.api.servicios;

import com.example.api.model.Titles;
import com.example.api.repository.TitlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TitleService {

    @Autowired
    private TitlesRepository titles;

    public List<Titles> getAll() {
        return this.titles.findAll();
    }

    public void delete(Long id) {
        this.titles.deleteById(id);
    }

    public Optional<Titles> getById(Long id) {
        return this.titles.findById(id);
    }

    public boolean save(Titles s) {
        this.titles.saveAndFlush(s);
        return true;
    }

}
