package movie.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import movie.system.dto.PaginatedResponse;
import movie.system.dto.UserDTO;
import movie.system.entity.User;
import movie.system.repository.UserRepository;
import movie.system.service.EmailService;
import movie.system.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/auth/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;

//    @GetMapping("/get")
//    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam (required = false) String use, @RequestParam (required = false) String search, @RequestParam(defaultValue = "1") int pageNumber,
//            @RequestParam(defaultValue = "10") int pageSize)) {
//    	List<UserDTO> userDTOs= userService.getAllUsers(use, search, pageNumber, pageSize);
//    	return new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
//    }

    @GetMapping("/get")
    public ResponseEntity<PaginatedResponse<UserDTO>> getAllUsers(
            @RequestParam(required = false) String usd,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        
        PaginatedResponse<UserDTO> response = userService.getAllUsers(usd, search, pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }

    
    @PostMapping("/post")
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	User user2 = userService.saveUser(user);
    	return new ResponseEntity<User>(user2, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getByUserId(@PathVariable Long userId) {
        User user = userService.getByIdUser(userId);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<User> updateByUserId(@PathVariable Long userId, @RequestBody User user) {
        User user2 = userService.updateByUser(userId, user);
        return new ResponseEntity<User>(user2, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteByUser(@PathVariable Long userId) {
         userService.DeletByUser(userId);
        return new ResponseEntity<String>("Deleted Sucessfully", HttpStatus.OK);
    }
    
    
//    @PostMapping("/verify")
//    public String verifyUser(@RequestParam String email, @RequestParam String code) {
//        User user = userRepository.findByEmail(email);
//
//        if (user == null) {
//            return "User not found";
//        }
//
//        if (user.isVerified()) {
//            return "User already verified";
//        }
//
//        if (user.getVerificationCode().equals(code)) {
//            user.setVerified(true);
//            user.setVerificationCode(null); // Clear the code after success
//            userRepository.save(user);
//
//            // Optionally send confirmation email
//            emailService.sendRegistrationSuccessEmail(user.getEmail(), user.getUsername());
//
//            return "User verified and registered successfully!";
//        } else {
//            return "Invalid verification code";
//        }
//    }
    
    
    @PostMapping("/verify")
    public String verifyUser(@RequestParam String email, @RequestParam String code) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return "User not found.";
        }

        if (user.isVerified()) {
            return "User already verified.";
        }

        if (user.getVerificationCode().equals(code)) {
            user.setVerified(true);
            user.setVerificationCode(null);
            userRepository.save(user);

            emailService.sendRegistrationSuccessEmail(user.getEmail(), user.getUsername());

            return "User verified and registered successfully!";
        } else {
            return "Invalid verification code.";
        }
    }

    
    
//    @PostMapping("/verify")
//    public ResponseEntity<String> verifyUser(@RequestParam String mobileno, @RequestParam String code) {
//        User user = userRepository.findByMobileno(mobileno);
//
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        if (user.isVerified()) {
//            return ResponseEntity.ok("User already verified");
//        }
//
//        if (user.getVerificationCode() != null && user.getVerificationCode().equals(code)) {
//            user.setVerified(true);
//            user.setVerificationCode(null); // Clear OTP after success
//            userRepository.save(user);
//
//            return ResponseEntity.ok("User verified and registered successfully!");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code");
//        }
//    }

    
          
}
