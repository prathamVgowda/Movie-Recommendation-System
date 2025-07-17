package movie.system.service;

import java.util.List;

import movie.system.dto.UserDTO;
import movie.system.entity.User;

public interface UserService 
{

	public User saveUser(User user);
	
	public List<UserDTO> getAllUsers();
	
	public User getByIdUser(Long userId);
	
	public User updateByUser(Long userId, User user);
	
	public String DeletByUser(Long userId);
}
