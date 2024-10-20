    package com.api.movie.entities;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Size;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import com.fasterxml.jackson.annotation.JsonFormat;

    import java.time.Instant;
    import java.time.LocalDate;
    import java.util.Set;

    @Getter
    @Setter
    @Entity
    @NoArgsConstructor // Required for JPA
    @Table(name="actor")
    public class Actor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotNull(message = "Name cannot be null")
        @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
        private String name;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDate birthDate;


        // Many-to-Many relationship with Movie
        @ManyToMany(mappedBy = "actors")
        @JsonIgnore
        private Set<Movie> movies;

        // Constructor
        public Actor(String name, LocalDate birthDate) {
            this.name = name;
            this.birthDate = birthDate;
        }
    }