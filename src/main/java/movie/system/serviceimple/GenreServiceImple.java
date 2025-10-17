package movie.system.serviceimple;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.system.entity.Genre;
import movie.system.exception.ResourceNotFoundException;
import movie.system.repository.GenreRepository;
import movie.system.service.GenreService;


@Service
public class GenreServiceImple implements GenreService
{
	
	private static final Logger logger = LoggerFactory.getLogger(GenreServiceImple.class);
	
	@Autowired
    private GenreRepository genreRepository;

	 @Override
	    public Genre createGenre(Genre genre) {
	        logger.info("Creating new genre with name: {}", genre.getGenre_name());
	        Genre savedGenre = genreRepository.save(genre);
	        logger.info("Genre created successfully with ID: {}", savedGenre.getGenreId());
	        return savedGenre;
	    }

	    @Override
	    public List<Genre> getAllGenres() {
	        logger.info("Fetching all genres");
	        List<Genre> genres = genreRepository.findAll();
	        logger.info("Fetched {} genres", genres.size());
	        return genres;
	    }

	    @Override
	    public Genre getByIdGenre(Integer genreId) {
	        logger.info("Fetching genre by ID: {}", genreId);
	        Genre genre = genreRepository.findById(genreId)
	                .orElseThrow(() -> {
	                    logger.error("Genre not found with ID: {}", genreId);
	                    return new ResourceNotFoundException("Genre with the given ID not found", 404, LocalDateTime.now());
	                });
	        logger.debug("Found genre: {}", genre.getGenre_name());
	        return genre;
	    }

	    @Override
	    public Genre updateGenre(Integer genreId, Genre genre) {
	        logger.info("Updating genre with ID: {}", genreId);
	        Genre existingGenre = genreRepository.findById(genreId)
	                .orElseThrow(() -> {
	                    logger.error("Genre not found for update with ID: {}", genreId);
	                    return new ResourceNotFoundException("Genre with the given ID not found", 404, LocalDateTime.now());
	                });
	        existingGenre.setGenreId(genre.getGenreId());
	        existingGenre.setGenre_name(genre.getGenre_name());
	        Genre updatedGenre = genreRepository.save(existingGenre);
	        logger.info("Genre updated successfully with ID: {}", updatedGenre.getGenreId());
	        return updatedGenre;
	    }

	    @Override
	    public String deleteGenre(Integer genreId) {
	        logger.info("Deleting genre with ID: {}", genreId);
	        if (!genreRepository.existsById(genreId)) {
	            logger.error("Genre not found for deletion with ID: {}", genreId);
	            throw new ResourceNotFoundException("Genre with the given ID not found", 404, LocalDateTime.now());
	        }
	        genreRepository.deleteById(genreId);
	        logger.info("Genre deleted successfully with ID: {}", genreId);
	        return "Genre deleted successfully";
	    }
    
	    @Override
	    public List<Genre> searbyGenrename(String genreName) {
	        logger.info("Fetching all genres");
	        return genreRepository.searchbygenrename(genreName);
	    }

    
}
