package movie.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import movie.system.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}