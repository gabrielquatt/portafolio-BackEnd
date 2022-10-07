package com.example.api.servicios;

import com.example.api.model.Experience;
import com.example.api.model.Person;
import com.example.api.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository person;

    public List<Person> getAll() {
        return this.person.findAll();
    }

    public Optional<Person> findPerson(Long id) {
        return this.person.findById(id);
    }

    @Transactional
    public boolean update(Person p) {
        this.person.saveAndFlush(p);
        return true;
    };

    public Optional<Person> getById(Long id) {
        return this.person.findById(id);
    }
}
