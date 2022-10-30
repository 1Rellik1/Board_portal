package board.games.security.controllers;


import board.games.security.entities.User;
import board.games.security.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {

    /**
     * Хэдер хронящий токен.
     */
    private final static String AUTHORIZATION_HEADER = "Authorization";

    private final TokenService tokenService;

    public AuthenticationController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    /**
     * Метод проверяющий действует ли токен
     *
     * @return В случае действия токена возвращает время до его кончсания, иначе 401 ошибку
     */
    @GetMapping("/isTokenNotExpired")
    public ResponseEntity<Long> isTokenNotExpired() {
        return tokenService.isTokenExpired();
    }

    /**
     * Метод авторизации
     *
     * @param request данные для авторизации
     * @return токенБ либо ошибка 401
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User request) {
        return tokenService.createToken(request);
    }

}
