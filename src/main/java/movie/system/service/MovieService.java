package movie.system.service;

import java.util.List;

import movie.system.entity.Movie;

public interface MovieService 
{

	public Movie createMovie(Movie movie);
	
	public List<Movie> getAllMovies(String genre, String title, Integer year);
	
	public Movie getByIdMovie(Long movieId);
	
	public Movie updateByMovie(Long movieId, Movie movie);
	
	public String deletByMovie(Long movieId);
	
	
	
}
