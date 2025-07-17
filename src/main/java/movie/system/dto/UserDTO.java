package movie.system.dto;

import java.util.List;

public class UserDTO 
{
private Long userId;
    
    private String username;
    private String email;
    private String password;
    private List<RatingDTO> ratings;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<RatingDTO> getRatings() {
		return ratings;
	}
	public void setRatings(List<RatingDTO> ratings) {
		this.ratings = ratings;
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDTO(Long userId, String username, String email, String password, List<RatingDTO> ratings) {
		super();
		this.userId = userId;
		this.username = username;
		this.email = email;
		this.password = password;
		this.ratings = ratings;
	}
    
	
    
}
