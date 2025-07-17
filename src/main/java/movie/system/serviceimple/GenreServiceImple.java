package movie.system.serviceimple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.system.entity.Genre;
import movie.system.exception.ResourceNotFoundException;
import movie.system.repository.GenreRepository;
import movie.system.service.GenreService;


@Service
public class GenreServiceImple implements GenreService
{
	@Autowired
    private GenreRepository genreRepository;

	@Override
	public Genre createGenre(Genre genre) 
	{
		Genre genres = genreRepository.save(genre);
		return genres;
	}

	@Override
	public List<Genre> getAllGenres() 
	{
		List<Genre> genre = genreRepository.findAll();
		return genre;
	}

	@Override
	public Genre getByIdGenre(Integer genreId) {
		System.out.println("Searching for Genre with ID: " + genreId);
	    return genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Genre with the given ID not found", 404));
	}


	@Override
	public Genre updateGenre(Integer genreId, Genre genre) 
	{
		Genre genres = genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Genre with the given ID not found", 404));
		genres.setGenerId(genre.getGenerId());
		genres.setGenre_name(genre.getGenre_name());
		return genreRepository.save(genres);
	}

	@Override
	public String deleteGenre(Integer genreId) {
	    if (!genreRepository.existsById(genreId)) 
	    {
	        throw new ResourceNotFoundException("Genre with the given ID not found", 404);
	    }
	    genreRepository.deleteById(genreId);

	    return "Genre deleted successfully";
	}


    
    
}
