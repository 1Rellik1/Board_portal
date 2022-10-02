package board.games.security.controllers;

import board.games.security.entities.User;
import board.games.security.entities.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path ="/lol", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<User> getAllUsers(){
        return userRepository.findAll();

    }
}
