package movie.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movie.system.dto.MovieDTO;
import movie.system.entity.Movie;
import movie.system.service.MovieService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/get")
    public ResponseEntity<List<MovieDTO> > getAllMovies() {
        List<MovieDTO> movie= movieService.getAllMovies();
        return new ResponseEntity<List<MovieDTO>>(movie, HttpStatus.OK);
    }
    
    @GetMapping("/gets")
    public ResponseEntity<List<Movie> > getAllMoviess() {
        List<Movie> movie= movieService.getAllMoviess();
        return new ResponseEntity<List<Movie>>(movie, HttpStatus.OK);
    }

    
    @PostMapping("/post")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie)
    {
    	Movie movie2 = movieService.createMovie(movie);
    	return new ResponseEntity<Movie>(movie2, HttpStatus.CREATED);
    }

    
    @GetMapping("/get/{movieId}")
    public ResponseEntity<Movie> getByMovieI(@PathVariable Long movieId)
    {
    	Movie movie=  movieService.getByIdMovie(movieId);
    	return new ResponseEntity<Movie>(movie, HttpStatus.OK);
    }
    
    @PutMapping("/update/{movieId}")
    public ResponseEntity<Movie> putMethodName(@PathVariable Movie movie, @RequestBody Long movieId) 
    {
    	Movie movie2 = movieService.updateByMovie(movieId, movie);
    	return new ResponseEntity<Movie>(movie2, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<String> deleteById(Long movieId)
    {
    	 movieService.deletByMovie(movieId);
    	 return new ResponseEntity<String>("Delted Sucessfully", HttpStatus.OK);
    }
}