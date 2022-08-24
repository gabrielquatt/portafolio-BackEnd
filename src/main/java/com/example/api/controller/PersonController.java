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

    @RequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Person> allPersonaByID(@PathVariable(value = "id") Long id) {
        return this.ps.findPerson(id);
    }

    @RequestMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> allPersonas() {
        return this.ps.getAll();
    }

    @PutMapping("UPDATE/{id}")
    public ResponseEntity<?> updatePerson(@RequestBody Person p) {
        Optional<Person> person = this.ps.findPerson(p.getId_Person());
        if(!person.isEmpty()){
            if (this.ps.update(p))
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
