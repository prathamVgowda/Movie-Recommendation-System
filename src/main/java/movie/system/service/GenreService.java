package movie.system.service;

import java.util.List;

import movie.system.entity.Genre;

public interface GenreService 
{

    public Genre createGenre(Genre genre);

    public List<Genre> getAllGenres();

    public Genre getByIdGenre(Integer genreId);

    public Genre updateGenre(Integer genreId, Genre genreDTO);

    public String deleteGenre(Integer genreId);
}

