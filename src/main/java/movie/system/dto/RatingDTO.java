package movie.system.dto;

import movie.system.entity.Rating;

public class RatingDTO 
{

	private Long ratingId;
    private Long userId;
    private String username;  // You can include only necessary user details
    private Long movieId;
    private String movieTitle;  // You can include only necessary movie details
    private float rating;
    private String review;
	public Long getRatingId() {
		return ratingId;
	}
	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
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
	public RatingDTO(Long ratingId, Long userId, String username, Long movieId, String movieTitle, float rating,
			String review) {
		super();
		this.ratingId = ratingId;
		this.userId = userId;
		this.username = username;
		this.movieId = movieId;
		this.movieTitle = movieTitle;
		this.rating = rating;
		this.review = review;
	}
	public RatingDTO(Rating rating) {
	    this.ratingId = rating.getRatingId();
	    this.rating = rating.getRating();
	    this.review = rating.getReview();

	    if (rating.getUser() != null) {
	        this.userId = rating.getUser().getUserId();
	        this.username = rating.getUser().getUsername();
	    }

	    if (rating.getMovie() != null) {
	        this.movieId = rating.getMovie().getMovieId();
	        this.movieTitle = rating.getMovie().getTitle();
	    }
	}

    
}
