package movie.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.system.entity.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
