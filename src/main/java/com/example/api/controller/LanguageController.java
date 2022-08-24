package com.example.api.controller;
import com.example.api.model.Language;
import com.example.api.servicios.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
    @RestController
    @RequestMapping("api/language/")
    public class LanguageController {

        @Autowired
        private LanguageService ls;

        @GetMapping(value = "GET/all", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<Language> allLanguage() {
            return ls.getAll();
        }

        @DeleteMapping("DELETE/{id}")
        public ResponseEntity<?> deleteLanguage(@PathVariable(value = "id") Long id) {
            Optional<Language> language = this.ls.getById(id);
            if (language.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            this.ls.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @PostMapping("POST")
        public ResponseEntity<?> postLanguage(@RequestBody Language l) {
            if (this.ls.save(l))
                return new ResponseEntity<>(HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        @PutMapping("UPDATE")
        public ResponseEntity<?> updateLanguage(@RequestBody Language l) {
            Optional<Language> language = this.ls.getById(l.getId_Lenguage());
            if(!language.isEmpty()){
                if (this.ls.save(l))
                    return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
}
