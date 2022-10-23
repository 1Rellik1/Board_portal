package board.games.first.game.entity.repo;

import board.games.first.game.entity.Session;
import board.games.first.game.enums.MoveStatus;
import board.games.first.game.enums.SessionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, String> {

    boolean existsSessionById(String id);
}
