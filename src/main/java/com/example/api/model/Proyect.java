package com.example.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Proyect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proyect;
    private String name;
    private String link;
    private String description;
    private String imgProyect;
    private Long imgId;
}
