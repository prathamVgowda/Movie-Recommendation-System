package movie.system.service;

import java.util.List;

import movie.system.dto.RatingDTO;
import movie.system.entity.Rating;

public interface RatingService 
{

	public Rating createRating(Rating rating);
	
	public List<RatingDTO> getAllRating();
	
	public Rating getByIdRating(Long ratingId);
	
	public Rating updateByRating(Long ratingId, Rating rating);
	
	public String deletByRating(Long ratingId);
}
