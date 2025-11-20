package com.example.EMS_v2.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // ---- Constructor ----
    protected Department() {}
    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // ---- Getters ----
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    // ---- Setters ----
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
