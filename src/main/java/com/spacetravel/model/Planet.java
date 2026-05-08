package com.spacetravel.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "planet")
@Data
public class Planet {
    @Id
    private String id; // Например, MARS

    @Column(length = 500, nullable = false)
    private String name;
}