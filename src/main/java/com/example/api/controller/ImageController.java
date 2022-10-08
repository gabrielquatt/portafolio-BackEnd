package com.example.api.controller;

import com.example.api.model.Imagen;
import com.example.api.model.Mensaje;
import com.example.api.servicios.CloudinaryService;
import com.example.api.servicios.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/cloudinary/")
@CrossOrigin
public class ImageController {

    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    ImagenService imagenService;


    @GetMapping("findByName/{name}")
    public ResponseEntity<Imagen> getByname(@PathVariable("name")String name){
        Optional<Imagen> img = imagenService.getByName(name);
        return new ResponseEntity(img, HttpStatus.OK);
    }

    @GetMapping("list")
    public ResponseEntity<List<Imagen>> list(){
        List<Imagen> list = imagenService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile)throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if(bi == null){
            return new ResponseEntity(new Mensaje("imagen no válida"), HttpStatus.BAD_REQUEST);
        }
        Map result = cloudinaryService.upload(multipartFile);
        Imagen imagen =
                new Imagen((String)result.get("original_filename"),
                        (String)result.get("url"),
                        (String)result.get("public_id"));
        imagenService.save(imagen);
        return new ResponseEntity(imagen, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id)throws IOException {
        if(!imagenService.exists(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Imagen imagen = imagenService.getOne(id).get();
        Map result = cloudinaryService.delete(imagen.getImagenId());
        imagenService.delete(id);
        return new ResponseEntity(new Mensaje("imagen eliminada"), HttpStatus.OK);
    }
}
