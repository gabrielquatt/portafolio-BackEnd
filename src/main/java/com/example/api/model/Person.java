package com.example.api.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Person;
    private String name;
    private String last_name;
    private String birthday;
    private String mail;
    private String about;
    private String ocupation;
    private String img_perfil;
    private Long img_id;

}
