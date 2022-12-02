package user.info.service.entities.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import user.info.service.entities.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, String> {


    Optional<User> findByUserName(String login);

    Optional<User> findByEmail(String login);

    Optional<User> getUserById(String id);
}
