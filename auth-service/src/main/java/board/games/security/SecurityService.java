package board.games.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Класс старта приложения
 */
@SpringBootApplication
public class SecurityService {
    /**
     * Старт приложения
     * @param args
     * аргументы
     */
    public static void main(String[] args) {
        SpringApplication.run(SecurityService.class, args);
    }
}
