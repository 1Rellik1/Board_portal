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
     * Фабрика объектов приложения.
     */
    private final BeanFactory beanFactory;

    /**
     * Репозиторий пользователей
     */
    private final UserRepository userRepository;

    /**
     * Валидатор уникальности нового пользователя.
     */
    private final UserUniqueValidator userUniqueValidator;

    public UserService(PasswordEncoder passwordEncoder,
                       BeanFactory beanFactory,
                       UserRepository userRepository,
                       UserUniqueValidator userUniqueValidator) {
        this.passwordEncoder = passwordEncoder;
        this.beanFactory = beanFactory;
        this.userRepository = userRepository;
        this.userUniqueValidator = userUniqueValidator;
    }


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

    public String addNewUser(User user) {
        String resulOfValidation = userUniqueValidator.validate(user);
        if (resulOfValidation == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.saveAndFlush(user);
            return null;
        }
        return resulOfValidation;
    }

    public String updateUserUser(User user) {
        String resulOfValidation = userUniqueValidator.validate(user);
        if (resulOfValidation == null) {
            Optional<User> optionalUserFromDB = userRepository.getUserById(user.getId());
            if (optionalUserFromDB.isEmpty()) {
                return "Пользователь не найден";
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
            return null;
        }
        return resulOfValidation;
    }

}
