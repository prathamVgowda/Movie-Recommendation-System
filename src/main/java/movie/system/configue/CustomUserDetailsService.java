package movie.system.configue;

import movie.system.entity.User;
import movie.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.findAllByUsername(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        if (users.size() > 1) {
            throw new UsernameNotFoundException("Multiple users found with username: " + username);
        }

        User user = users.get(0);

        String role = "ROLE_" + user.getRoles().toUpperCase();

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(role))
        );
    }


    
    
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        // ✅ Avoid printing user.toString() — it may access lazy fields like ratings
//        // System.out.println(user); // ❌ remove this line if it exists
//
//        return new org.springframework.security.core.userdetails.User(
//            user.getUsername(),
//            user.getPassword(),
//            Collections.singleton(new SimpleGrantedAuthority(user.getRoles()))
//        );
//    }
}
