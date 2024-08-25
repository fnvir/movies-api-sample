package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Movie;
import com.example.demo.Repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Find movie by IMDb ID
    public Optional<Movie> getMovieByImdbId(String imdbId) {
        return movieRepository.findByImdbId(imdbId);
    }

    // Get all movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Get movie by ID
    public Optional<Movie> getMovieById(ObjectId id) {
        return movieRepository.findById(id);
    }

    // Add a new movie
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Update movie
    public Optional<Movie> updateMovie(ObjectId id, Movie newMovieDetails) {
        return movieRepository.findById(id).map(movie -> {
            movie.setTitle(newMovieDetails.getTitle());
            movie.setReleaseDate(newMovieDetails.getReleaseDate());
            movie.setTrailerLink(newMovieDetails.getTrailerLink());
            movie.setPoster(newMovieDetails.getPoster());
            movie.setGenres(newMovieDetails.getGenres());
            movie.setBackdrops(newMovieDetails.getBackdrops());
            movie.setReviews(newMovieDetails.getReviews());
            return movieRepository.save(movie);
        });
    }

    // Delete movie by ID
    public void deleteMovie(ObjectId id) {
        movieRepository.deleteById(id);
    }
}
