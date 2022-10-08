package com.example.api.controller;

import com.example.api.model.Experience;
import com.example.api.model.Person;
import com.example.api.security.services.IUploadFileService;
import com.example.api.servicios.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/person/")
public class PersonController {

    @Autowired
    private PersonService ps;

    @Autowired
    private IUploadFileService uploadService;

    @RequestMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Person> allPersonaByID(@PathVariable(value = "id") Long id) {
        return this.ps.findPerson(id);
    }

    @RequestMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> allPersonas() {
        return this.ps.getAll();
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updatePerson(@RequestBody Person p) {
        Optional<Person> person = this.ps.findPerson(p.getId_Person());
        if(!person.isEmpty()){
            if (this.ps.update(p))
                return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
    @PostMapping("upload")
    public ResponseEntity<?> uploadWork_Experience(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){

        Map<String, Object> response = new HashMap<>();
        Optional<Person> person = this.ps.getById(id);

        if(!archivo.isEmpty()) {
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadService.copiar(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnterior = person.get().getImg_perfil();
            uploadService.eliminar(nombreFotoAnterior);
            person.get().setImg_perfil(nombreArchivo);
            ps.update(person.get());

            response.put("Work Experience", person.get());
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
