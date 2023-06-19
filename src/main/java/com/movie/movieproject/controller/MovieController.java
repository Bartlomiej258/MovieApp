package com.movie.movieproject.controller;

import com.movie.movieproject.model.Category;
import com.movie.movieproject.model.Movie;
import com.movie.movieproject.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MovieController {

    private MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movies/new")
    public String showMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("categories", Category.values());
        return "movie-form";
    }

    @PostMapping("/movies")
    public String addMovie(Movie movie) {
        movieRepository.save(movie);
        return "redirect:movies/new";
    }

    @GetMapping("/movies")
    public String showMovieList(@RequestParam(value = "category", required = false) String category, Model model) {
        List<Movie> movies;

        if (category != null && !category.isEmpty()) {
            Category movieCategory = Category.valueOf(category);
            movies = movieRepository.findByCategory(movieCategory);
            model.addAttribute("filteredCategory", movieCategory);
        } else {
            movies = movieRepository.findAll();
        }

        model.addAttribute("movies", movies);
        return "movie-list";
    }

    @GetMapping("/movies/{id}")
    public String showDetails(@PathVariable("id") Long id, Model model) {
        Movie movie = movieRepository.findById(id).orElse(null);
        model.addAttribute("movie", movie);
        return "movie-details";
    }

    @GetMapping("/movies/{id}/edit")
    public String editMovie(@PathVariable("id") Long id, Model model) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));

        model.addAttribute("movie", movie);
        model.addAttribute("categories", Category.values());

        return "movie-edit";
    }

    @PostMapping("/movies/{id}/update")
    public String updateMovie(@PathVariable("id") Long id, @ModelAttribute("movie") @Validated Movie updatedMovie, BindingResult result) {
        if (result.hasErrors()) {
            return "movie-edit";
        }

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie ID: " + id));

        movie.setTitle(updatedMovie.getTitle());
        movie.setPremiere(updatedMovie.getPremiere());
        movie.setDescription(updatedMovie.getDescription());
        movie.setCategory(updatedMovie.getCategory());

        movieRepository.save(movie);

        return "redirect:/movies";
    }

}
