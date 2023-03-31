package game.info.service.domain.repo;

import game.info.service.domain.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameInfoRepository extends JpaRepository<GameInfo, String> {

    @Query(value = "SELECT * FROM game_info WHERE string_to_array(game_info.style,',') && ?1 "
            + "AND game_info.max_number_of_players >= ?2 AND string_to_array(game_info.game_type,',')  && ?3;",
            nativeQuery = true)
    List<GameInfo> algorithmOfChoosingGames(List<String> gameStyle, int maxNumberOfPlayers, List<String> gameType);
}
