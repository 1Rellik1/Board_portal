package board.games.security.services;

import board.games.security.entities.User;
import board.games.security.entities.repo.UserRepository;
import board.games.security.validator.UserUniqueValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Optional;

/**
 * Сервис работы с пользователями
 */
@Service
public class UserService implements UserDetailsService {
    /**
     * Хэдер хронящий токен.
     */
    private final static String AUTHORIZATION_HEADER = "Authorization";

    @Value("${url.user.info.service}")
    private String userInfoServiceUrl;
    /**
     * Сообщение об удаление пользователя
     */
    private static final String errorDeleteMassage = "Пользователь не может быть удален";


    /**
     * Сообщение об удаление пользователя
     */
    private static final String deleteMassage = "Пользователь удален";

    /**
     * Кодировщик пароля.
     */
    private final PasswordEncoder passwordEncoder;


    /**
     * Репозиторий пользователей
     */
    private final UserRepository userRepository;

    /**
     * Валидатор уникальности нового пользователя.
     */
    private final UserUniqueValidator userUniqueValidator;

    /**
     * Конструктор
     *
     * @param passwordEncoder     Кодировщик пароля
     * @param userRepository      Репозиторий пользователей
     * @param userUniqueValidator Валидатор уникальности нового пользователя
     */
    public UserService(PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       UserUniqueValidator userUniqueValidator) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userUniqueValidator = userUniqueValidator;
    }

    /**
     * Загрузка данных пользователя для авторизации
     *
     * @param username имя пользователя/почта
     * @return Данные пользователя для авторизации
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user;
        if (username.contains("@")) {
            user = userRepository.findByEmail(username);
        } else {
            user = userRepository.findByUserName(username);
        }

        if (user.isEmpty()) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        System.out.println("Found User: " + username);
        return user.get();
    }

    /**
     * Добавление нового пользователя
     *
     * @param user Данные пользователя
     * @return результат добавления
     */
    public boolean addNewUser(User user) {
        if (userUniqueValidator.validate(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .POST(HttpRequest.BodyPublishers.ofString(user.toJsonFormat()))
                        .uri(new URI(userInfoServiceUrl + "/register"))
                        .header("Content-Type", "application/json")
                        .build();
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    return false;
                }
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException("Не удалось добавить пользователя");
            }
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }

    /**
     * Обновление данных пользователя
     *
     * @param user Данные пользователя
     * @return результат обновления
     */
    public boolean updateUserUser(User user) {
        if (userUniqueValidator.validate(user)) {
            Optional<User> optionalUserFromDB = userRepository.getUserById(user.getId());
            if (optionalUserFromDB.isEmpty()) {
                throw new RuntimeException("Пользователь не найден");
            }
            var userFromDB = optionalUserFromDB.get();
            if (user.getUsername() != null) {
                userFromDB.setUserName(user.getUsername());
            }
            if (user.getEmail() != null) {
                userFromDB.setUserName(user.getEmail());
            }
            if (user.getPassword() != null) {
                userFromDB.setUserName(passwordEncoder.encode(user.getPassword()));
            }
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .POST(HttpRequest.BodyPublishers.ofString(user.toJsonFormat()))
                        .uri(new URI(userInfoServiceUrl + "/updateUser"))
                        .header(AUTHORIZATION_HEADER, ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                                .getRequest().getHeader(AUTHORIZATION_HEADER))
                        .header("Content-Type", "application/json")
                        .build();
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    return false;
                }
            } catch (URISyntaxException | IOException | InterruptedException e) {
                throw new RuntimeException("Не удалось обновить пользователя");
            }
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }

    private final String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
}
