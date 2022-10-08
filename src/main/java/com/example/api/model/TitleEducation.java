package com.example.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
public class TitleEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Education;
    private String school;
    private String title;
    private String condition;

}
