package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Movie;
import com.example.demo.Service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	private MovieService m;
	
	@Autowired
	public MovieController(MovieService m) {
		this.m=m;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Movie>> getAllMovies() {
		return new ResponseEntity<List<Movie>>(m.getAllMovies(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Movie>> getMovie(@PathVariable ObjectId id) {
		return new ResponseEntity<Optional<Movie>>(m.getMovieById(id),HttpStatus.OK);
	}
	
	@GetMapping("/imdb/{imdbId}")
	public ResponseEntity<Optional<Movie>> getMovie(@PathVariable("imdbId") String  id) {
		return new ResponseEntity<Optional<Movie>>(m.getMovieByImdbId(id),HttpStatus.OK);
	}
	
}
