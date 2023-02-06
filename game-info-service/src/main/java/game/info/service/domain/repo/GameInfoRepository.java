package game.info.service.domain.repo;

import game.info.service.domain.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameInfoRepository extends JpaRepository<GameInfo, String> {
}
