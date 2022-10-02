package board.games.security.controllers;


import board.games.security.entities.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
@RequestMapping(value = "/api")
public class AuthenticationController {

    /**
     * Хэдер хронящий токен.
     */
    private final static String AUTHORIZATION_HEADER = "Authorization";

    /**
     * Менеджер авторизации.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Кодировщик токена.
     */
    private final JwtEncoder jwtEncoder;

    /**
     * Декодировщик токена.
     */
    private final JwtDecoder jwtDecoder;


    /**
     * Конструктор
     *
     * @param authenticationManager Менеджер авторизации
     * @param jwtEncoder            Кодировщик токена
     * @param jwtDecoder            Декодировщик токена
     */
    public AuthenticationController(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    /**
     * Метод проверяющий действует ли токен
     *
     * @return В случае действия токена возвращает время до его кончсания, иначе 401 ошибку
     */
    @GetMapping("/isTokenNotExpired")
    public ResponseEntity<Long> isTokenNotExpired() {
        var token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest().getHeader(AUTHORIZATION_HEADER);
        token = token.substring(7, token.length());
        var decodedToken = jwtDecoder.decode(token);
        var now = Instant.now();
        var expiresAt = decodedToken.getExpiresAt();
        if (expiresAt.isAfter(now)) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(ChronoUnit.HOURS.between(now, expiresAt));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Метод авторизации
     *
     * @param request данные для авторизации
     * @return токенБ либо ошибка 401
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User request) {
        try {
            Authentication authentication;
            if (request.getUsername() != null) {
                authentication =
                        authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            } else {
                authentication =
                        authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            }

            var user = (User) authentication.getPrincipal();

            var now = Instant.now();
            var expiry = 36000L;

//            var scope =
//                    authentication.getAuthorities().stream()
//                            .map(GrantedAuthority::getAuthority)
//                            .collect(joining(" "));

            var claims =
                    JwtClaimsSet.builder()
                            .expiresAt(now.plusSeconds(expiry))
                            .subject(String.format("%s,%s", user.getId(), user.getUsername()))
//                            .claim("roles", scope)
                            .build();

            var token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(user);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
