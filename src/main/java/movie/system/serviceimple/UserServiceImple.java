package movie.system.serviceimple;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import movie.system.dto.PaginatedResponse;
import movie.system.dto.RatingDTO;
import movie.system.dto.UserDTO;
import movie.system.entity.User;
import movie.system.exception.ResourceNotFoundException;
import movie.system.repository.UserRepository;
import movie.system.service.UserService;

@Service
public class UserServiceImple implements UserService
{

	@Autowired
    private UserRepository userRepository;

//	@Override
//	public PaginatedResponse<UserDTO> getAllUsers(String usd, String search, int pageNumber, int pageSize) {
//	    List<User> users = userRepository.findAll();
//
//	    List<User> filteredUsers = users.stream()
//	        .filter(us -> usd == null || usd.isEmpty() || usd.equalsIgnoreCase(us.getUsername()))
//	        .filter(us -> search == null || search.isEmpty() || us.getUsername().toLowerCase().contains(search.toLowerCase()))
//	        .toList();
//
//	    long totalRecords = filteredUsers.size();
//
//	    List<UserDTO> paginatedUsers = filteredUsers.stream()
//	        .skip((long) (pageNumber - 1) * pageSize)
//	        .limit(pageSize)
//	        .map(user -> new UserDTO(
//	            user.getUserId(),
//	            user.getUsername(),
//	            user.getEmail(),
//	            user.getPassword(),
//	            user.getRatings().stream()
//	                .map(RatingDTO::new)
//	                .toList()
//	        ))
//	        .toList();
//
//	    return new PaginatedResponse<>(paginatedUsers, totalRecords);
//	}
	
	
	
	@Override
	public PaginatedResponse<UserDTO> getAllUsers(String usd, String search, int pageNumber, int pageSize) 
	{
	    List<User> users = userRepository.findAll();

	    List<User> filteredUsers = users.stream()
	        .filter(us -> usd == null || usd.isEmpty() || usd.equalsIgnoreCase(us.getUsername()))
	        .filter(us -> search == null || search.isEmpty() || us.getUsername().toLowerCase().contains(search.toLowerCase()))
	        .toList();

	    long totalRecords = filteredUsers.size();

	    List<UserDTO> paginatedUsers = filteredUsers.stream()
	        .skip((long) (pageNumber - 1) * pageSize)
	        .limit(pageSize)
	        .map(user -> new UserDTO(
	            user.getUserId(),
	            user.getUsername(),
	            user.getEmail(),
	            user.getPassword(),
	            user.getRatings().stream()
	                .map(RatingDTO::new)
	                .toList()
	        ))
	        .toList();

	    return new PaginatedResponse<>(paginatedUsers, totalRecords, pageNumber, pageSize);
	}




	@Override
    public User saveUser(User user) 
	{
        return userRepository.save(user);
    }

	@Override
	public User getByIdUser(Long userId) 
	{
		User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with the given ID not found", 404, LocalDateTime.now()));
		return user;
	}

	@Override
	public User updateByUser(Long userId, User user) 
	{
		User user2 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with the given ID not found", 404, LocalDateTime.now()));
		user2.setUserId(user.getUserId());
		user2.setEmail(user.getEmail());
		user2.setUsername(user.getEmail());
		user2.setPassword(user.getPassword());
		user2.setRatings(user.getRatings());
		return userRepository.save(user2);
	}

	@Override
	public String DeletByUser(Long userId) 
	{
		if (userRepository.existsById(userId))
		{
			throw new ResourceNotFoundException("User with the given ID not found", 404, LocalDateTime.now());
		}
		userRepository.deleteById(userId);
		return "Deleted"+ userId + " Sucessfully";
		
	}

}
