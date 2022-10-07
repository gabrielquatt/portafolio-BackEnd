package com.example.api.controller;

import com.example.api.model.Experience;
import com.example.api.model.Skill;
import com.example.api.security.services.IUploadFileService;
import com.example.api.servicios.SkillService;
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
@RequestMapping("api/skill/")
public class SkillController {

    @Autowired
    private SkillService ss;

    @Autowired
    private IUploadFileService uploadService;

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
        this.uploadService.eliminar(this.ss.getById(id).get().getImg()); //eliminar logo
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

    @PostMapping("/upload")
    public ResponseEntity<?> uploadWork_Experience(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){

        Map<String, Object> response = new HashMap<>();
        Optional<Skill> skill = this.ss.getById(id);

        if(!archivo.isEmpty()) {
            String nombreArchivo = null;
            try {
                nombreArchivo = uploadService.copiar(archivo);
            } catch (IOException e) {
                response.put("mensaje", "Error al subir la imagen");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String nombreFotoAnterior = skill.get().getImg();
            uploadService.eliminar(nombreFotoAnterior);
            skill.get().setImg(nombreArchivo);
            ss.save(skill.get());

            response.put("Skill", skill.get());
            response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}

