package user.info.service.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import user.info.service.entities.User;
import user.info.service.entities.repo.UserRepository;

import java.util.Optional;

/**
 * Сервис работы с пользователями
 */
@Service
public class UserService {

    /**
     * Репозиторий пользователей
     */
    private final UserRepository userRepository;

    /**
     * Конструктор
     * <p>
     * Кодировщик пароля
     *
     * @param userRepository
     *            Репозиторий пользователей
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Добавление нового пользователя
     *
     * @param user
     *            Данные пользователя
     * @return результат добавления
     */
    @Transactional
    public boolean addNewUser(User user) {
        userRepository.saveAndFlush(user);
        return true;
    }

    /**
     * Обновление данных пользователя
     *
     * @param user
     *            Данные пользователя
     * @return результат обновления
     */
    @Transactional
    public boolean updateUserUser(User user) {
        Optional<User> optionalUserFromDB = userRepository.getUserById(user.getId());
        if (optionalUserFromDB.isEmpty()) {
            throw new RuntimeException("Пользователь не найден");
        }
        var userFromDB = optionalUserFromDB.get();
        if (user.getUserName() != null) {
            userFromDB.setUserName(user.getUserName());
        }
        if (user.getEmail() != null) {
            userFromDB.setUserName(user.getEmail());
        }
        if (user.getTrustRating() != null) {
            userFromDB.setTrustRating(user.getTrustRating());
        }
        userRepository.saveAndFlush(user);
        return true;
    }
}
