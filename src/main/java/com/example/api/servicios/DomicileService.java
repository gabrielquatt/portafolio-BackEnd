package com.example.api.servicios;

import com.example.api.model.Domicile;
import com.example.api.repository.DomicileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomicileService {

    @Autowired
    private DomicileRepository domicile;

    public List<Domicile> getAll() {
        return this.domicile.findAll();
    }

    public void delete(Long id) {
        this.domicile.deleteById(id);
    }

    public Optional<Domicile> getById(Long id) {
        return  this.domicile.findById(id);
    }

    public boolean save(Domicile c) {
        this.domicile.saveAndFlush(c);
        return true;
    }
}
