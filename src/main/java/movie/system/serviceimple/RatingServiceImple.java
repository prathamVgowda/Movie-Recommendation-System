package movie.system.serviceimple;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
	
	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Rating createRating(Rating rating) {
	    System.out.println("Looking for rating with ID: " + rating.getMovie().getMovieId());

	    // Fetch the movie based on the movieId
	    Movie movie = movieRepository.findById(rating.getMovie().getMovieId())
	            .orElseThrow(() -> {
	                System.err.println("Movie not found for ID: " + rating.getMovie().getMovieId());
	                return new RuntimeException("Movie not found");
	            });

	    // Fetch the user based on the userId
	    User user = userRepository.findById(rating.getUser().getUserId())
	            .orElseThrow(() -> {
	                System.err.println("User not found for ID: " + rating.getUser().getUserId());
	                return new RuntimeException("User not found");
	            });

	    // Set the movie and user on the rating object
	    rating.setMovie(movie);
	    rating.setUser(user);

	    // Save the rating and return it
	    return ratingRepository.save(rating);
	}
	
	
//	@Override
//	public Rating createRating(Rating rating) 
//	{	
//		Rating rating2 = ratingRepository.save(rating);
//		return rating2;
//	}

//	@Override
//	public List<Rating> getAllRating() 
//	{
//		List<Rating> list = ratingRepository.findAll();
//		return list;
//	}
	
	public List<RatingDTO> getAllRating() {
	    List<Rating> ratings = ratingRepository.findAll();
	    List<RatingDTO> ratingDTOs = ratings.stream()
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
	    return ratingDTOs;
	}


	@Override
	public Rating getByIdRating(Long ratingId) 
	{
		Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating with the given ID not found", 404,  LocalDateTime.now()));
		return rating;
	}

	@Override
	public Rating updateByRating(Long ratingId, Rating rating) 
	{
		Rating rating2= ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating with the given ID not found", 404,  LocalDateTime.now()));
		rating2.setMovie(rating.getMovie());
		rating2.setRating(rating.getRating());
		rating2.setReview(rating.getReview());
		rating2.setUser(rating.getUser());
		rating2.setRatingId(rating.getRatingId());
		return ratingRepository.save(rating2);
	}

	@Override
	public String deletByRating(Long ratingId) 
	{
		if(ratingRepository.existsById(ratingId))
		{
			throw new ResourceNotFoundException("Rating with the given ID not found", 404,  LocalDateTime.now());
		}
		ratingRepository.deleteById(ratingId);
		return "Deleted Successfully";
	}

}
