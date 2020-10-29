package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<Trainer> trainers;
    @OneToMany
    private List<Trainee> trainees;
}
