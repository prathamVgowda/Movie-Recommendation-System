package movie.system.serviceimple;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }

	@Override
	public List<UserDTO> getAllUsers() {
	    List<User> users = userRepository.findAll();
	    List<UserDTO> userDTOs = users.stream()
	                                   .map(user -> new UserDTO(
	                                       user.getUserId(),
	                                       user.getUsername(),
	                                       user.getEmail(),
	                                       user.getPassword(),
	                                       user.getRatings().stream()
	                                           .map(rating -> new RatingDTO(rating))
	                                           .collect(Collectors.toList())
	                                   ))
	                                   .collect(Collectors.toList());
	    return userDTOs;
	}

//	@Override
//	public List<UserDTO> getAllUsers() {
//	    List<User> users = userRepository.findAll();
//	    List<UserDTO> userDTOs = users.stream()
//	                                   .map(user -> new UserDTO(
//	                                       user.getUserId(),
//	                                       user.getUsername(),
//	                                       user.getEmail(),
//	                                       user.getPassword(),
//	                                       user.getRatings().stream()
//	                                           .map(rating -> new RatingDTO(rating))
//	                                           .collect(Collectors.toList())
//	                                   ))
//	                                   .collect(Collectors.toList());
//	    return userDTOs;
//	}
	
	@Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

	@Override
	public User getByIdUser(Long userId) {
		User user= userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with the given ID not found", 404));
		return user;
	}

	@Override
	public User updateByUser(Long userId, User user) {
		User user2 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with the given ID not found", 404));
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
			throw new ResourceNotFoundException("User with the given ID not found", 404);
		}
		userRepository.deleteById(userId);
		return "Deleted"+ userId + " Sucessfully";
		
	}

}
