package movie.system.serviceimple;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import movie.system.entity.Genre;
import movie.system.entity.Movie;
import movie.system.exception.ResourceNotFoundException;
import movie.system.repository.GenreRepository;
import movie.system.repository.MovieRepository;
import movie.system.service.MovieService;

@Service
public class MovieServiceImple implements MovieService
{
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
    private GenreRepository generesRepository;

	@Override
	public Movie createMovie(Movie movie) 
	{
	    Genre genre = generesRepository.findById(movie.getGenre().getGenerId())
	            .orElseThrow(() -> {return new ResourceNotFoundException("Genre not found", 404, LocalDateTime.now());});
	    movie.setGenre(genre);

	    return movieRepository.save(movie); 
	}

	
	@Override
	public List<Movie> getAllMovies(String genre, String title, Integer year) 
	{
	    List<Movie> movies = movieRepository.findAll();
	    return movies.stream()
	        .filter(movie -> genre == null || genre.isEmpty() || genre.equalsIgnoreCase(movie.getGenre().getGenre_name()))
	        .filter(movie -> title == null || title.isEmpty() || movie.getTitle().toLowerCase().contains(title.toLowerCase()))
	        .filter(movie -> year == null || movie.getReleaseYear() == year)
	        .toList();
	}

	
	@Override
	public Movie getByIdMovie(Long movieId) 
	{
		Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Movie with the given ID not found", 404,  LocalDateTime.now()));
		return movie;
	}

	@Override
	public Movie updateByMovie(Long movieId, Movie movie) 
	{
		Movie movie2 = movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Movie with the given ID not found", 404,  LocalDateTime.now()));
		movie2.setMovieId(movie.getMovieId());
		movie2.setDirector(movie.getDirector());
		movie2.setDescription(movie.getDescription());
		movie2.setGenre(movie.getGenre());
		movie2.setTitle(movie.getTitle());
		movie2.setRatings(movie.getRatings());
		movie2.setDuration(movie.getDuration());
		movie2.setReleaseYear(movie.getReleaseYear());
		return movieRepository.save(movie2);
	}

	@Override
	public String deletByMovie(Long movieId) 
	{
		if(movieRepository.existsById(movieId))
		{
			throw new ResourceNotFoundException("Movie with the given ID not found", 404,  LocalDateTime.now());
		}

		movieRepository.deleteById(movieId);
		return "Deleted"+ movieId +  "Sucessfully";
	}
	
}
