package com.example.api.controller;

import com.example.api.model.Proyect;
import com.example.api.servicios.ProyectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/proyect/")
public class ProyectController {

    @Autowired
    private ProyectService ps;

    @GetMapping(value = "GET/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Proyect> allProyect() {
        return ps.getAll();
    }

    @DeleteMapping("DELETE/{id}")
    public ResponseEntity<?> deleteProyect(@PathVariable(value = "id") Long id) {
        Optional<Proyect> proyect = this.ps.getById(id);
        if (proyect.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        this.ps.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("POST")
    public ResponseEntity<?> postProyect(@RequestBody Proyect p) {
        if (this.ps.save(p))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("UPDATE")
    public ResponseEntity<?> updateProyect(@RequestBody Proyect p) {
        Optional<Proyect> proyect = this.ps.getById(p.getId_proyect());
        if(!proyect.isEmpty()){
            if (this.ps.save(p))
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
