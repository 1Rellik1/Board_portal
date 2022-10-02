package board.games.security.services;

import board.games.security.base.UserDeatilsImpl;
import board.games.security.entities.User;
import board.games.security.entities.repo.UserRepository;
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
    private  final PasswordEncoder passwordEncoder;

    /**
     * Фабрика объектов приложения.
     */
    private final BeanFactory beanFactory;

    /**
     * Репозиторий пользователей
     */
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, BeanFactory beanFactory, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.beanFactory = beanFactory;
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);

        if (user.isEmpty()) {
            System.out.println("User not found! " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        System.out.println("Found User: " + username);
        final UserDeatilsImpl result = beanFactory.getBean(UserDeatilsImpl.class);
        result.initialize(user.get());
        return result;
    }

    public void addNewUser(User user){
            user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            userRepository.save(user);
    }
}
