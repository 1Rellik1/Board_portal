package user.info.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Класс старта приложения
 */
@SpringBootApplication
public class UserInfoService {
    /**
     * Старт приложения
     * @param args
     * аргументы
     */
    public static void main(String[] args) {
        SpringApplication.run(UserInfoService.class, args);
    }
}
