package movie.system.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import movie.system.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>
{
	public Optional<Genre> findById(Integer genreId);
	
	@Query("SELECT gn FROM Genre gn WHERE LOWER(gn.genre_name) LIKE LOWER(CONCAT('%', :genre_name, '%'))")
	List<Genre> searchbygenrename(@Param("genre_name") String genre_name);

}
