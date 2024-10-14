package com.api.movie.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Many-to-Many relationship with Movie
    @ManyToMany(mappedBy = "genres")
    @JsonBackReference
//    @JsonIgnore
    private Set<Movie> movies;

    // Constructor
    public Genre(String name) {
        this.name = name;
    }
}
