package board.games.security.controllers;

import board.games.security.entities.User;
import board.games.security.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    /**
     * Сервис работы с пользователями
     */
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody User request) {
        String result = userService.addNewUser(request);
        if (result == null) {
            return ResponseEntity.ok().body("Пользователь успешно добавлен");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }

    @PatchMapping("/updateUser")
    public ResponseEntity<String> updateLogin(@RequestBody User request) {
        String result = userService.updateUserUser(request);
        if (result == null) {
            return ResponseEntity.ok().body("Данные пользователя успешно обновлены");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
    }
}
