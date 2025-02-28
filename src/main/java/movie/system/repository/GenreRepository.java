package movie.system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.system.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>
{
	public Optional<Genre> findById(Integer genreId);

}
