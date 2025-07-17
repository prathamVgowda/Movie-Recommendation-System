package movie.system.dto;

import movie.system.entity.Genre;

public class GenreDTO 
{
	private int GenerId;
    private String genre_name;
    
	public int getGenerId() {
		return GenerId;
	}
	public void setGenerId(int generId) {
		GenerId = generId;
	}
	public String getGenre_name() {
		return genre_name;
	}
	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}
	public GenreDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GenreDTO(int generId, String genre_name) {
		super();
		GenerId = generId;
		this.genre_name = genre_name;
	}
	public GenreDTO(Genre genre) {
		// TODO Auto-generated constructor stub
	}

	
    
    

}
