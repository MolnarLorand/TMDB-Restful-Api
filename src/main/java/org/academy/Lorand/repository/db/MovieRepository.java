package org.academy.Lorand.repository.db;

import org.academy.Lorand.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie findByTitle(String title);



}
