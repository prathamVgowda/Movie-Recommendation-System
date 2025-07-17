package movie.system.serviceimple;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.system.dto.MovieDTO;
import movie.system.dto.RatingDTO;
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
	public Movie createMovie(Movie movie) {
	    System.out.println("Looking for genre with ID: " + movie.getGenre().getGenerId());
	    Genre genre = generesRepository.findById(movie.getGenre().getGenerId())
	            .orElseThrow(() -> {
	                System.err.println("Genre not found for ID: " + movie.getGenre().getGenerId());
	                return new RuntimeException("Genre not found");
	            });

	    movie.setGenre(genre);

	    return movieRepository.save(movie); 
	}

//	 public Movie createMovie(Movie movie) {
//	        // Get the Genre based on the generId in the request
//	        Genre genre = generesRepository.findById(movie.getGenre().getGenerId())
//	                .orElseThrow(() -> new RuntimeException("Genre not found"));
//
//	        // Set the genre and save the movie
//	        movie.setGenre(genre);
//	        return movieRepository.save(movie);
//	    }
//	@Override
//	public Movie createMovie(Movie movie) 
//	{
//		return movieRepository.save(movie) ;
//	}


//	@Override
//	public List<MovieDTO> getAllMovies() 
//	{
//		List<Movie> movies = movieRepository.findAll();
//		List<MovieDTO> movieDTOs = movies.stream().map(movie -> new MovieDTO(
//				movie.getMovieId(),
//				movie.getTitle(),
//				movie.getGenre(),
//				movie.getReleaseYear(),
//				movie.getDirector(),
//				movie.getDescription(),
//				movie.getRatings().stream()
//                .map(rating -> new RatingDTO(rating))
//                .collect(Collectors.toList())
//				))
//				.collect(Collectors.toList());
//		return movieDTOs;
//	}

	@Override
	public List<Movie> getAllMovies() 
	{
		return movieRepository.findAll();
	}
	
	@Override
	public Movie getByIdMovie(Long movieId) {

		Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Movie with the given ID not found", 404));
		return movie;
	}

	@Override
	public Movie updateByMovie(Long movieId, Movie movie) {

		Movie movie2 = movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Movie with the given ID not found", 404));
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
	public String deletByMovie(Long movieId) {
		if(movieRepository.existsById(movieId))
		{
			throw new ResourceNotFoundException("Movie with the given ID not found", 404);
		}

		movieRepository.deleteById(movieId);
		return "Deleted"+ movieId +  "Sucessfully";
	}
	
}
