package movie.system.service;

import java.util.List;

import movie.system.dto.MovieDTO;
import movie.system.entity.Movie;

public interface MovieService 
{

	public Movie createMovie(Movie movie);
	
	public List<MovieDTO> getAllMovies();
	
	public List<Movie> getAllMoviess();
	
	public Movie getByIdMovie(Long movieId);
	
	public Movie updateByMovie(Long movieId, Movie movie);
	
	public String deletByMovie(Long movieId);
	
	
}
