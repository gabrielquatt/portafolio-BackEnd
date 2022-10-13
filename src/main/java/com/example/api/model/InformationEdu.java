package com.example.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class InformationEdu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_title;
    private String school;
    private String name;
    private String condition;

}
