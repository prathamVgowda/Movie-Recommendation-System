package movie.system.serviceimple;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.system.dto.RatingDTO;
import movie.system.entity.Movie;
import movie.system.entity.Rating;
import movie.system.entity.User;
import movie.system.exception.ResourceNotFoundException;
import movie.system.repository.MovieRepository;
import movie.system.repository.RatingRepository;
import movie.system.repository.UserRepository;
import movie.system.service.RatingService;

@Service
public class RatingServiceImple implements RatingService
{
	private static final Logger logger = LoggerFactory.getLogger(RatingServiceImple.class);

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	@Override
    public Rating createRating(Rating rating) {
        logger.info("Creating rating for movieId={} by userId={}",
                rating.getMovie().getMovieId(), rating.getUser().getUserId());

        Movie movie = movieRepository.findById(rating.getMovie().getMovieId())
                .orElseThrow(() -> {
                    logger.error("Movie not found with ID: {}", rating.getMovie().getMovieId());
                    return new ResourceNotFoundException("Movie not found", 404, LocalDateTime.now());
                });

        User user = userRepository.findById(rating.getUser().getUserId())
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", rating.getUser().getUserId());
                    return new ResourceNotFoundException("User not found", 404, LocalDateTime.now());
                });

        rating.setMovie(movie);
        rating.setUser(user);

        Rating savedRating = ratingRepository.save(rating);
        logger.info("Rating created successfully with ratingId={}", savedRating.getRatingId());
        return savedRating;
    }

    @Override
    public List<RatingDTO> getRatingsSortedByRatingDesc() {
        logger.info("Fetching all ratings sorted by rating descending");

        List<Rating> ratings = ratingRepository.findAll();

        List<RatingDTO> result = ratings.stream()
                .sorted((r1, r2) -> Double.compare(r2.getRating(), r1.getRating()))
                .map(rating -> new RatingDTO(
                        rating.getRatingId(),
                        rating.getUser().getUserId(),
                        rating.getUser().getUsername(),
                        rating.getMovie().getMovieId(),
                        rating.getMovie().getTitle(),
                        rating.getRating(),
                        rating.getReview()
                ))
                .collect(Collectors.toList());

        logger.debug("Fetched {} ratings", result.size());
        return result;
    }

    public List<RatingDTO> getAllRating(String user) {
        logger.info("Fetching all ratings filtered by user='{}'", user);

        List<Rating> ratings = ratingRepository.findAll();

        List<RatingDTO> ratingDTOs = ratings.stream()
                .filter(rate -> user == null || user.isEmpty() || user.equalsIgnoreCase(rate.getUser().getUsername()))
                .map(rating -> new RatingDTO(
                        rating.getRatingId(),
                        rating.getUser().getUserId(),
                        rating.getUser().getUsername(),
                        rating.getMovie().getMovieId(),
                        rating.getMovie().getTitle(),
                        rating.getRating(),
                        rating.getReview()
                ))
                .collect(Collectors.toList());

        logger.debug("Returning {} ratings after filter", ratingDTOs.size());
        return ratingDTOs;
    }

    @Override
    public Rating getByIdRating(Long ratingId) {
        logger.info("Fetching rating by ID: {}", ratingId);

        Rating rating = ratingRepository.findById(ratingId)
                .orElseThrow(() -> {
                    logger.error("Rating not found with ID: {}", ratingId);
                    return new ResourceNotFoundException("Rating with the given ID not found", 404, LocalDateTime.now());
                });

        logger.debug("Found rating with ID: {}", ratingId);
        return rating;
    }

    @Override
    public Rating updateByRating(Long ratingId, Rating rating) {
        logger.info("Updating rating with ID: {}", ratingId);

        Rating rating2 = ratingRepository.findById(ratingId)
                .orElseThrow(() -> {
                    logger.error("Rating not found for update with ID: {}", ratingId);
                    return new ResourceNotFoundException("Rating with the given ID not found", 404, LocalDateTime.now());
                });

        rating2.setMovie(rating.getMovie());
        rating2.setRating(rating.getRating());
        rating2.setReview(rating.getReview());
        rating2.setUser(rating.getUser());
        rating2.setRatingId(rating.getRatingId());

        Rating updatedRating = ratingRepository.save(rating2);
        logger.info("Rating updated successfully with ID: {}", updatedRating.getRatingId());
        return updatedRating;
    }

    @Override
    public String deletByRating(Long ratingId) {
        logger.info("Deleting rating with ID: {}", ratingId);

        if (!ratingRepository.existsById(ratingId)) {
            logger.error("Rating with ID {} not found for deletion", ratingId);
            throw new ResourceNotFoundException("Rating with the given ID not found", 404, LocalDateTime.now());
        }

        ratingRepository.deleteById(ratingId);
        logger.info("Rating with ID {} deleted successfully", ratingId);
        return "Deleted Successfully";
    }

}
