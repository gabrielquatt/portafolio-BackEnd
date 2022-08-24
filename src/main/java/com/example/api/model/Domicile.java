package com.example.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Domicile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Domicile;
    private String street;
    private String location;
    private String province;

}
