package com.example.api.controller;

import com.example.api.model.Titles;
import com.example.api.servicios.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/information/")
public class InformationController {

    @Autowired
    private TitleService ts;
    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Titles> allInfoEducation(){return ts.getAll();}

    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTitleEducation(@PathVariable(value = "id") Long id) {
        Optional<Titles> titleEducation = this.ts.getById(id);
        if (titleEducation.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        this.ts.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
    @PostMapping("save")
    public ResponseEntity<?> postTitleEducation(@RequestBody Titles t) {
        if (this.ts.save(t))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @CrossOrigin(origins = "https://portafolio-web-gq-31166.web.app")
    @PutMapping("update")
    public ResponseEntity<?> updateTitleEducation(@RequestBody Titles t) {
        Optional<Titles> titleEducation = this.ts.getById(t.getIdTitle());
        if(!titleEducation.isEmpty()){
            if (this.ts.save(t))
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
