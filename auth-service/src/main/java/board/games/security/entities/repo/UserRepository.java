package board.games.security.entities.repo;

import board.games.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findByUserName(String login);

    Optional<User> findByEmail(String login);

    Optional<User> getUserById(String id);
}
