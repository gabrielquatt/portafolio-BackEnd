package com.example.api.servicios;

import com.example.api.model.Proyect;
import com.example.api.repository.ProyectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectService {

    @Autowired
    private ProyectRepository proyect;

    public List<Proyect> getAll() {
        return this.proyect.findAll();
    }

    public void delete(Long id) {
        this.proyect.deleteById(id);
    }

    public Optional<Proyect> getById(Long id) {
        return  this.proyect.findById(id);
    }

    public boolean save(Proyect p) {
        this.proyect.saveAndFlush(p);
        return true;
    }
}
