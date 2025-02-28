package movie.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import movie.system.dto.UserDTO;
import movie.system.entity.User;
import movie.system.service.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
    	List<UserDTO> userDTOs= userService.getAllUsers();
    	return new ResponseEntity<List<UserDTO>>(userDTOs, HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<User> createUser(@RequestBody User user) {
    	User user2 = userService.saveUser(user);
    	return new ResponseEntity<User>(user2, HttpStatus.CREATED);
    }

    @GetMapping("get/{userId}")
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
        
}
