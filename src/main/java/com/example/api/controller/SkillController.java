package com.example.api.controller;

import com.example.api.model.Skill;
import com.example.api.servicios.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/skill/")
public class SkillController {

    @Autowired
    private SkillService ss;

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Skill> allSkill() {
        return ss.getAll();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteSkill(@PathVariable(value = "id") Long id) {
        Optional<Skill> skill = this.ss.getById(id);
        if (skill.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        this.ss.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<?> postSkill(@RequestBody Skill s) {
        if (this.ss.save(s))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateSkill(@RequestBody Skill s) {
        Optional<Skill> skill = this.ss.getById(s.getIdSkill());
        if(!skill.isEmpty()){
            if (this.ss.save(s))
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}

