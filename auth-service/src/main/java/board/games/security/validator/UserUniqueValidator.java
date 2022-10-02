package board.games.security.validator;

import board.games.security.entities.User;
import board.games.security.entities.repo.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserUniqueValidator {
    private final UserRepository userRepository;

    public UserUniqueValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String validate(User user) {
        var sameUser = userRepository.findByUserName(user.getUsername());
        if (!sameUser.isEmpty()) {
            return "Пользователь с данным login уже существует";
        } else {
            sameUser = userRepository.findByEmail( user.getEmail());
            if (!sameUser.isEmpty()) {
                return "Пользователь с данным email уже существует";
            } else {
                return null;
            }
        }
    }
}
