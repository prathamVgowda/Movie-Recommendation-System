package movie.system.dto;

import movie.system.entity.Movie;

public class RatingRequest 
{

	private UserDTO userId;   // user_id in the payload
    private Long movieId;  // movie_id in the payload
    private float rating;  // rating in the payload
    private String review;
    
    
	public UserDTO getUserId() {
		return userId;
	}
	public void setUserId(UserDTO userDTO) {
		this.userId = userDTO;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public void setMovieId(Movie movie) {
		// TODO Auto-generated method stub
		
	}
    
    
}
