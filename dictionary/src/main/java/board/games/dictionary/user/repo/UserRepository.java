package board.games.dictionary.user.repo;

import board.games.dictionary.user.User;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends Repository<User, Integer> {

    Optional<User> findByUserName(String username);

    List<User> getUserById(int id);

    List<User> findAll();
}
