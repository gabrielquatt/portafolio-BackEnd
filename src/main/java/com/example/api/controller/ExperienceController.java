package com.example.api.controller;

import com.example.api.model.Experience;
import com.example.api.security.services.IUploadFileService;
import com.example.api.servicios.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/experience/")
public class ExperienceController {

    @Autowired
    private ExperienceService es;

    @Autowired
    private IUploadFileService uploadService;

    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Experience> allWork_Experience() {
        return es.getAll();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteWork_Experience(@PathVariable(value = "id") Long id) {
        Optional<Experience> work_Experience = this.es.getById(id);
        if (work_Experience.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        this.uploadService.eliminar(this.es.getById(id).get().getLogo()); //eliminar logo
        this.es.delete(id);//eliminar elemento
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("save")
    public ResponseEntity<?> postWork_Experience(@RequestBody Experience w) {
            if (this.es.save(w)){
                return new ResponseEntity<>(HttpStatus.OK);
            }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping("update")
    public ResponseEntity<?> updateWork_Experience(@RequestBody Experience w) {
            if (this.es.save(w))
                return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("upload")
    public ResponseEntity<?> uploadWork_Experience(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){

        Map<String, Object> response = new HashMap<>();
        Optional<Experience> experience = this.es.getById(id);

        if(!archivo.isEmpty()) {
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadService.copiar(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnterior = experience.get().getLogo();
            uploadService.eliminar(nombreFotoAnterior);
            experience.get().setLogo(nombreArchivo);
            es.save(experience.get());

            response.put("Work Experience", experience.get());
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}

