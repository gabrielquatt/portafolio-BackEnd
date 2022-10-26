package com.example.api.controller;

import com.example.api.model.Experience;
import com.example.api.servicios.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/experience/")
public class ExperienceController {

    @Autowired
    private ExperienceService es;

    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Experience> allWork_Experience() {
        return es.getAll();
    }

    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteWork_Experience(@PathVariable(value = "id") Long id) {
        Optional<Experience> work_Experience = this.es.getById(id);
        if (work_Experience.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        this.es.delete(id);//eliminar elemento
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
    @PostMapping("save")
    public ResponseEntity<?> postWork_Experience(@RequestBody Experience w) {
            if (this.es.save(w)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateWork_Experience(@RequestBody Experience w) {
            if (this.es.save(w))
                return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }


}

