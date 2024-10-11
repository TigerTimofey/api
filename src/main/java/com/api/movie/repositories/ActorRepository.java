package com.api.movie.repositories;

import com.api.movie.entities.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
    List<Actor> findByNameContainingIgnoreCase(String name);
}
