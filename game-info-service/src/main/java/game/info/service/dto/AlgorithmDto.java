package game.info.service.dto;

import java.util.List;

public class AlgorithmDto {
    private List<String> gameStyle;
    private int maxNumberOfPlayers;
    private List<String> gameType;

    public List<String> getGameStyle() {
        return gameStyle;
    }

    public void setGameStyle(List<String> gameStyle) {
        this.gameStyle = gameStyle;
    }

    public int getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public void setMaxNumberOfPlayers(int maxNumberOfPlayers) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
    }

    public List<String> getGameType() {
        return gameType;
    }

    public void setGameType(List<String> gameType) {
        this.gameType = gameType;
    }
}
