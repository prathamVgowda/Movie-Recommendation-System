package movie.system.service;


import movie.system.dto.PaginatedResponse;
import movie.system.dto.UserDTO;
import movie.system.entity.User;

public interface UserService 
{

	public User saveUser(User user);
	
	public PaginatedResponse<UserDTO> getAllUsers(String usd, String search, int pageNumber, int pageSize);
	
	public User getByIdUser(Long userId);
	
	public User updateByUser(Long userId, User user);
	
	public String DeletByUser(Long userId);
}
