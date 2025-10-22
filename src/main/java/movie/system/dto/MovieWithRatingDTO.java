package movie.system.dto;

import movie.system.entity.Genre;

public class MovieWithRatingDTO 
{
	private Long movieId;
    private String title;
    private Genre genre;
    private Integer releaseYear;
    private Integer duration;
    private String director;
    private String description;
    private String rating;
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public MovieWithRatingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieWithRatingDTO(Long movieId, String title, Genre genre, Integer releaseYear, Integer duration,
			String director, String description, String rating) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.duration = duration;
		this.director = director;
		this.description = description;
		this.rating = rating;
	}
	
	
	    

}
