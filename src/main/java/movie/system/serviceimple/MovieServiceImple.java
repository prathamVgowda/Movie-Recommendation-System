package movie.system.serviceimple;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(MovieServiceImple.class);

	
	@Override
    public Movie createMovie(Movie movie) 
	{
		Genre genre = generesRepository.findById(movie.getGenre().getGenreId()).orElseThrow(() -> {
		    logger.error("Genre not found with ID: {}", movie.getGenre().getGenreId());
		    return new ResourceNotFoundException("Genre not found", 404, LocalDateTime.now());
		});

        
        movie.setGenre(genre);
        Movie savedMovie = movieRepository.save(movie);
        logger.info("Movie created successfully with ID: {}", savedMovie.getMovieId());
        return savedMovie;
    }

    @Override
    public List<Movie> getAllMovies(String genre, String title, Integer year) {
        logger.info("Fetching all movies with filters - genre: {}, title: {}, year: {}", genre, title, year);
        List<Movie> movies = movieRepository.findAll();

        List<Movie> filteredMovies = movies.stream()
                .filter(movie -> genre == null || genre.isEmpty() || genre.equalsIgnoreCase(movie.getGenre().getGenre_name()))
                .filter(movie -> title == null || title.isEmpty() || movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(movie -> year == null || movie.getReleaseYear() == year)
                .toList();

        logger.info("Returning {} movies after filtering", filteredMovies.size());
        return filteredMovies;
    }

    @Override
    public Movie getByIdMovie(Long movieId) {
        logger.info("Fetching movie by ID: {}", movieId);
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> {
                    logger.error("Movie not found with ID: {}", movieId);
                    return new ResourceNotFoundException("Movie with the given ID not found", 404, LocalDateTime.now());
                });
        logger.debug("Found movie: {}", movie.getTitle());
        return movie;
    }

    @Override
    public Movie updateByMovie(Long movieId, Movie movie) {
        logger.info("Updating movie with ID: {}", movieId);
        Movie movie2 = movieRepository.findById(movieId)
                .orElseThrow(() -> {
                    logger.error("Movie not found for update with ID: {}", movieId);
                    return new ResourceNotFoundException("Movie with the given ID not found", 404, LocalDateTime.now());
                });

        movie2.setMovieId(movie.getMovieId());
        movie2.setDirector(movie.getDirector());
        movie2.setDescription(movie.getDescription());
        movie2.setGenre(movie.getGenre());
        movie2.setTitle(movie.getTitle());
        movie2.setRatings(movie.getRatings());
        movie2.setDuration(movie.getDuration());
        movie2.setReleaseYear(movie.getReleaseYear());

        Movie updatedMovie = movieRepository.save(movie2);
        logger.info("Movie updated successfully with ID: {}", updatedMovie.getMovieId());
        return updatedMovie;
    }

    @Override
    public String deletByMovie(Long movieId) {
        logger.info("Deleting movie with ID: {}", movieId);

        if (!movieRepository.existsById(movieId)) {
            logger.error("Movie with ID {} not found for deletion", movieId);
            throw new ResourceNotFoundException("Movie with the given ID not found", 404, LocalDateTime.now());
        }

        movieRepository.deleteById(movieId);
        logger.info("Movie with ID {} deleted successfully", movieId);
        return "Deleted " + movieId + " Successfully";
    }
	
}
