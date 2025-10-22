package movie.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import movie.system.dto.MovieWithRatingDTO;
import movie.system.entity.Movie;
import movie.system.service.MovieService;
import movie.system.service.RecommendationService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/auth/movies")
public class MovieController {
	
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    
    @Autowired
    private MovieService movieService;
    
    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/gets")
    public ResponseEntity<List<Movie>> getAllMovies(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer year) {

        logger.info("Fetching movies with filters - Genre: {}, Title: {}, Year: {}", genre, title, year);
	
        List<Movie> allMovies = movieService.getAllMovies(genre, title, year);
        logger.debug("Fetched {} movies", allMovies.size());

        return new ResponseEntity<>(allMovies, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        logger.info("Received request to create movie: {}", movie.getTitle());
        Movie savedMovie = movieService.createMovie(movie);
        logger.info("Movie created with ID: {}", savedMovie.getMovieId());
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    @GetMapping("/get/{movieId}")
    public ResponseEntity<Movie> getByMovieId(@PathVariable Long movieId) {
        logger.info("Fetching movie by ID: {}", movieId);
        Movie movie = movieService.getByIdMovie(movieId);
        logger.debug("Found movie: {}", movie.getTitle());
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @PutMapping("/update/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long movieId, @RequestBody Movie movie) {
        logger.info("Updating movie with ID: {}", movieId);
        Movie updatedMovie = movieService.updateByMovie(movieId, movie);
        logger.info("Movie updated: {}", updatedMovie.getTitle());
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<String> deleteById(@PathVariable Long movieId) {
        logger.info("Request to delete movie with ID: {}", movieId);
        movieService.deletByMovie(movieId);
        logger.info("Movie deleted with ID: {}", movieId);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
    
    
    @GetMapping("/top/{count}")
    public List<MovieWithRatingDTO> getTopRecommendedMovies(@PathVariable int count) {
        return recommendationService.recommendTopMovies(count);
    }
    

}