package board.games.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Класс старта приложения
 */
@SpringBootApplication
public class AuthService {
    /**
     * Старт приложения
     * @param args
     * аргументы
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthService.class, args);
    }
}
