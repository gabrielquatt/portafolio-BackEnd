package com.example.api.controller;

import com.example.api.model.Domicile;
import com.example.api.servicios.DomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/domicile/")
public class DomicileController {

    @Autowired
    private DomicileService ds;

    @GetMapping(value = "GET/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Domicile> allDomicile() {
        return ds.getAll();
    }

    @DeleteMapping("DELETE/{id}")
    public ResponseEntity<?> deleteDomicile(@PathVariable(value = "id") Long id) {
        Optional<Domicile> domicile = this.ds.getById(id);
        if (domicile.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        this.ds.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("POST")
    public ResponseEntity<?> postDomicile(@RequestBody Domicile c) {
        if (this.ds.save(c))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("UPDATE")
    public ResponseEntity<?> updateClient(@RequestBody Domicile c) {
        Optional<Domicile> client = this.ds.getById(c.getId_Domicile());
        if(!client.isEmpty()){
            if (this.ds.save(c))
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
