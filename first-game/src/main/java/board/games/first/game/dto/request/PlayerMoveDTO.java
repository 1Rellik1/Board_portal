package board.games.first.game.dto.request;

public class PlayerMoveDTO {
    private String sessionId;
    private String playerName;
    private Integer diceResult;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getDiceResult() {
        return diceResult;
    }

    public void setDiceResult(Integer diceResult) {
        this.diceResult = diceResult;
    }
}
