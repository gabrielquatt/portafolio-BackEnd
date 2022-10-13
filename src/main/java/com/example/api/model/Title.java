package com.example.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String school;
    private String title;
    private String condition;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
