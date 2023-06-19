package com.movie.movieproject.repository;

import com.movie.movieproject.model.Category;
import com.movie.movieproject.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByCategory(Category movieCategory);
}
