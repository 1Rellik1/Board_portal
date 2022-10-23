package board.games.first.game.dto.response;

import java.util.List;


public class MoveResultDTO {
    private PlayerPositionDTO player;
    private Integer diceResult;

    public PlayerPositionDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerPositionDTO player) {
        this.player = player;
    }

    public Integer getDiceResult() {
        return diceResult;
    }

    public void setDiceResult(Integer diceResult) {
        this.diceResult = diceResult;
    }
}
