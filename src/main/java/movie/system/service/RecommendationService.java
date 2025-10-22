package movie.system.service;

import java.util.List;

import movie.system.dto.MovieWithRatingDTO;
import movie.system.entity.Movie;

public interface RecommendationService 
{
	public List<MovieWithRatingDTO> recommendTopMovies(int count);
	
    public double calculateAverageRating(Movie movie);

}
