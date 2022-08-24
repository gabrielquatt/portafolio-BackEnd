package com.example.api.controller;
import com.example.api.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/")
public class UserController {

    @Autowired
    private UserService us;

    @GetMapping(value = "authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allTitleEducation(@PathVariable(value = "name") String name, @PathVariable(value = "pass") String pass) {
        if(us.authenticate(name,pass)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}