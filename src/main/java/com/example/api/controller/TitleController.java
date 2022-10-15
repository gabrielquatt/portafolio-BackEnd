package com.example.api.controller;
import com.example.api.model.Information;
import com.example.api.servicios.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/titles/")
public class TitleController {

    @Autowired
    private InformationService ts;

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Information> allTitleEducation() {
        return ts.getAll();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteTitleEducation(@PathVariable(value = "id") Long id) {
        Optional<Information> titleEducation = this.ts.getById(id);
        if (titleEducation.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        this.ts.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<?> postTitleEducation(@RequestBody Information t) {
        if (this.ts.save(t))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateTitleEducation(@RequestBody Information t) {
        Optional<Information> titleEducation = this.ts.getById(t.getId());
        if(!titleEducation.isEmpty()){
            if (this.ts.save(t))
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
