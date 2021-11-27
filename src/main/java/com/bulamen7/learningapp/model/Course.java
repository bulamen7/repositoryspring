package com.bulamen7.learningapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private int id;
   private String name;
   private String description;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
