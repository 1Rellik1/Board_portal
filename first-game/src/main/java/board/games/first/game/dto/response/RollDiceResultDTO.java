package board.games.first.game.dto.response;

import java.util.List;


public class RollDiceResultDTO {
    private PlayerBalanceDTO playerBalance;
    private PlayerPositionDTO player;
    private Integer diceResult;

    public PlayerBalanceDTO getPlayerBalance() {
        return playerBalance;
    }

    public void setPlayerBalance(PlayerBalanceDTO playerBalance) {
        this.playerBalance = playerBalance;
    }

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
