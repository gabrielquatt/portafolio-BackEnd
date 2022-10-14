package com.example.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Titles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTitle;
    private String name;
    private String institute;
    private String status;
}
