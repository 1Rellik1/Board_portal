package board.games.first.game.mapper;

import board.games.first.game.dto.response.MoveResultDTO;
import board.games.first.game.dto.response.PlayerBalanceDTO;
import board.games.first.game.dto.response.PlayerPositionDTO;
import board.games.first.game.dto.response.RollDiceResultDTO;

public class RollDicesMapper {

    public static RollDiceResultDTO rollResultTODTO(Integer diceResult, String playerName, int position, Long balance) {
        RollDiceResultDTO rollDiceResultDTO = new RollDiceResultDTO();
        rollDiceResultDTO.setPlayerBalance(new PlayerBalanceDTO(playerName, balance));
        rollDiceResultDTO.setDiceResult(diceResult);
        rollDiceResultDTO
                .setPlayer(new PlayerPositionDTO(playerName, position));
        return rollDiceResultDTO;


    }

    public static MoveResultDTO rollResultToMoveDTO(RollDiceResultDTO dto) {
        MoveResultDTO moveResultDTO = new MoveResultDTO();
        moveResultDTO.setDiceResult(dto.getDiceResult());
        moveResultDTO.setPlayer(dto.getPlayer());
        return moveResultDTO;


    }
}
