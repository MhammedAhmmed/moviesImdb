package mohamed.example.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{email}/{password}")
    ResponseEntity<Optional<User>> findUser(@PathVariable String email, @PathVariable String password){

        return new ResponseEntity<>(userService.getUser(email, password), HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<User> createUser(@RequestBody Map<String ,String>data){
        if(userService.existingEmail(data.get("email")))
            return null;
        return new ResponseEntity<>(userService.createUser(data), HttpStatus.OK);
    }
}
