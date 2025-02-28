package movie.system.dto;

import java.util.List;
import java.util.Set;

import movie.system.entity.Genre;

public class MovieDTO 
{

	private Long movieId;
    private String title;
    private List<Genre> genre;  // Corrected "Generes" to "Genre"
    private int releaseYear;
    private int duration;  // Duration in minutes or whatever unit
    private String director;
    private String description;
    private Set<RatingDTO> ratings;  // If you're using RatingDTO for proper abstraction of rating data
	public MovieDTO(Long movieId2, String title2, Genre genre2, int releaseYear2, String director2, String description2,
			List<RatingDTO> collect) {
		// TODO Auto-generated constructor stub
	}
	public MovieDTO(Long movieId2, String title2, Genre genre2, int releaseYear2, int duration2, String director2,
			String description2, Object object) {
		// TODO Auto-generated constructor stub
	}
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
	public List<Genre> getGenre() {
		return genre;
	}
	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
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
	public Set<RatingDTO> getRatings() {
		return ratings;
	}
	public void setRatings(Set<RatingDTO> ratings) {
		this.ratings = ratings;
	}
	
    
}
