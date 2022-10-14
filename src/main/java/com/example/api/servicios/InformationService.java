package com.example.api.servicios;
import com.example.api.model.Information;
import com.example.api.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InformationService {

    @Autowired
    private InformationRepository title;

    public List<Information> getAll() {
        return this.title.findAll();
    }

    public void delete(Long id) {
        this.title.deleteById(id);
    }

    public Optional<Information> getById(Long id) {
        return this.title.findById(id);
    }

    public boolean save(Information t) {
        this.title.saveAndFlush(t);
        return true;
    }


}
