package com.example.api.controller;

import com.example.api.model.Person;
import com.example.api.servicios.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/person/")
public class PersonController {

    @Autowired
    private PersonService ps;


    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
    @RequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Person> allPersonaByID(@PathVariable(value = "id") Long id) {
        return this.ps.findPerson(id);
    }

    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
    @RequestMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> allPersonas() {
        return this.ps.getAll();
    }

    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
    @PutMapping("update/{id}")
    public ResponseEntity<?> updatePerson(@RequestBody Person p, @PathVariable(value = "id") Long id) {
        Optional<Person> person = this.ps.findPerson(id);
        if(!person.isEmpty()){
            p.setId_Person(id);
            if (this.ps.update(p))
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
