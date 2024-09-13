package mohamed.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

     public Optional<User> getUser (String email, String password){
        Optional<User> user = userRepository.findUserByEmail(email);
        if(user.isPresent() && rightAuthentication(user, email, password)){
            return user;
        }
        return Optional.empty();
    }
    private Boolean rightAuthentication(Optional<User> user, String email, String password){
        if(user.isEmpty())
            return false;
        return user.get().getPassword().equals(password);
    }
    public Boolean existingEmail(String email){
         return userRepository.findUserByEmail(email).isPresent();
    }
    public User createUser(Map<String, String> data){
         User user = new User();
         user.setName(data.get("name"));
         user.setEmail(data.get("email"));
         user.setPassword(data.get("password"));
         return userRepository.insert(user);
    }
}
