package com.api.movie.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Many-to-Many relationship with Movie
    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies;

    // Constructor
    public Genre(String name) {
        this.name = name;
    }
}
