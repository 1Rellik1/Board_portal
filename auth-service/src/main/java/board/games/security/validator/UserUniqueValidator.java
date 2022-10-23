package board.games.security.validator;

import board.games.security.entities.User;
import board.games.security.entities.repo.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserUniqueValidator {
    /**
     * Репозиторий пользователей.
     */
    private final UserRepository userRepository;

    /**
     * Конструктор
     *
     * @param userRepository Репозиторий пользователей
     */
    public UserUniqueValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Валидация данных пользователя
     *
     * @param user Данные пользователя
     * @return результат валидации, если все норм, то true
     */
    public boolean validate(User user) {
        var sameUser = userRepository.findByUserName(user.getUsername());
        if (!sameUser.isEmpty()) {
            throw new RuntimeException("Пользователь с данным login уже существует") ;
        } else {
            sameUser = userRepository.findByEmail(user.getEmail());
            if (!sameUser.isEmpty()) {
                throw new RuntimeException( "Пользователь с данным email уже существует");
            } else {
                return true;
            }
        }
    }
}
