package movie.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.system.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer>
{

}
