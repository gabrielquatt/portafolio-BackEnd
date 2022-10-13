package com.example.api.controller;

import com.example.api.model.Title_Edu;
import com.example.api.servicios.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/education/")
public class EducationController {

    @Autowired
    private EducationService ts;

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Title_Edu> allTitleEducation() {
        return ts.getAll();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTitleEducation(@PathVariable(value = "id") Long id) {
        Optional<Title_Edu> titleEducation = this.ts.getById(id);
        if (titleEducation.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        this.ts.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<?> postTitleEducation(@RequestBody Title_Edu t) {
        if (this.ts.save(t)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateTitleEducation(@RequestBody Title_Edu t) {
        Optional<Title_Edu> titleEducation = this.ts.getById(t.getId());
        if(!titleEducation.isEmpty()){
            if (this.ts.save(t))
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
