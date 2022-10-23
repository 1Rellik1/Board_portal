package board.games.security.services;

import board.games.security.entities.User;
import board.games.security.entities.repo.UserRepository;
import board.games.security.validator.UserUniqueValidator;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Сервис работы с пользователями
 */
@Service
public class UserService implements UserDetailsService {

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
     * @param passwordEncoder
     * Кодировщик пароля
     * @param userRepository
     * Репозиторий пользователей
     * @param userUniqueValidator
     *  Валидатор уникальности нового пользователя
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
                throw new RuntimeException( "Пользователь не найден");
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
            userRepository.saveAndFlush(user);
            return true;
        }
        return false;
    }

}
