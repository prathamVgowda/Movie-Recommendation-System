package movie.system.serviceimple;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImple.class);

	@Autowired
    private UserRepository userRepository;

	
	@Override
    public PaginatedResponse<UserDTO> getAllUsers(String usd, String search, int pageNumber, int pageSize) {
        logger.info("Fetching all users with filter usd='{}' and search='{}'", usd, search);

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
                        user.getRatings().stream().map(RatingDTO::new).toList()))
                .toList();

        logger.debug("Returning {} users out of {} total records", paginatedUsers.size(), totalRecords);
        return new PaginatedResponse<>(paginatedUsers, totalRecords, pageNumber, pageSize);
    }

    @Override
    public User saveUser(User user) {
        logger.info("Saving new user: {}", user.getUsername());
        User savedUser = userRepository.save(user);
        logger.info("User saved with ID: {}", savedUser.getUserId());
        return savedUser;
    }

    @Override
    public User getByIdUser(Long userId) {
        logger.info("Fetching user by ID: {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", userId);
                    return new ResourceNotFoundException("User with the given ID not found", 404, LocalDateTime.now());
                });
        logger.debug("User found: {}", user.getUsername());
        return user;
    }

    @Override
    public User updateByUser(Long userId, User user) {
        logger.info("Updating user with ID: {}", userId);
        User user2 = userRepository.findById(userId)
                .orElseThrow(() -> {
                    logger.error("User not found for update, ID: {}", userId);
                    return new ResourceNotFoundException("User with the given ID not found", 404, LocalDateTime.now());
                });

        user2.setUserId(user.getUserId());
        user2.setEmail(user.getEmail());
        user2.setUsername(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setRatings(user.getRatings());

        User updated = userRepository.save(user2);
        logger.info("User updated successfully with ID: {}", updated.getUserId());
        return updated;
    }

    @Override
    public String DeletByUser(Long userId) {
        logger.info("Attempting to delete user with ID: {}", userId);
        if (!userRepository.existsById(userId)) {
            logger.error("User with ID {} not found for deletion", userId);
            throw new ResourceNotFoundException("User with the given ID not found", 404, LocalDateTime.now());
        }

        userRepository.deleteById(userId);
        logger.info("User with ID {} deleted successfully", userId);
        return "Deleted " + userId + " Successfully";
    }

}
