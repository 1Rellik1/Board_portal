package game.info.service.service;

import static game.info.service.params.ErrorMessage.GAME_INFO_NOT_FOUND;

import game.info.service.domain.GameInfo;
import game.info.service.domain.repo.GameInfoRepository;
import game.info.service.dto.GameInfoDto;
import game.info.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameInfoService {

    private final GameInfoRepository gameInfoRepository;

    public GameInfoService(GameInfoRepository gameInfoRepository) {
        this.gameInfoRepository = gameInfoRepository;
    }

    public List<GameInfoDto> getAllGameInfo() {
        return gameInfoRepository.findAll().stream().map(gameInfo -> {
            GameInfoDto gameInfoDto = new GameInfoDto();
            gameInfoDto.setName(gameInfo.getName());
            gameInfoDto.setImage(gameInfo.getImage());
            gameInfoDto.setType(gameInfo.getType());
            gameInfoDto.setUrl(gameInfo.getUrl());
            gameInfoDto.setId(gameInfo.getId());
            return gameInfoDto;
        }).toList();
    }

    public GameInfo uploadGameInfo(GameInfoDto gameInfoDto) {
        GameInfo gameInfo = new GameInfo();
        gameInfo.setName(gameInfoDto.getName());
        gameInfo.setImage(gameInfoDto.getImage());
        gameInfo.setUrl(gameInfoDto.getUrl());
        gameInfo.setType(gameInfoDto.getType());
        gameInfoRepository.saveAndFlush(gameInfo);
        return gameInfo;
    }

    public GameInfo updateGameInfo(GameInfoDto gameInfoDto) {
        GameInfo gameInfo = gameInfoRepository.findById(gameInfoDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(GAME_INFO_NOT_FOUND));
        if (gameInfoDto.getName() != null) {
            gameInfo.setName(gameInfoDto.getName());
        }
        if (gameInfoDto.getImage() != null) {
            gameInfo.setImage(gameInfoDto.getImage());
            gameInfo.setType(gameInfoDto.getType());
        }
        if (gameInfoDto.getUrl() != null) {
            gameInfo.setUrl(gameInfoDto.getUrl());
        }
        gameInfoRepository.saveAndFlush(gameInfo);
        return gameInfo;
    }

    public List<GameInfoDto>
            getGamesByAlgorithm(List<String> gameStyle, int maxNumberOfPlayers, List<String> gameType) {
        return gameInfoRepository.algorithmOfChoosingGames(gameStyle, maxNumberOfPlayers, gameType)
                .stream()
                .map(gameInfo -> {
                    GameInfoDto gameInfoDto = new GameInfoDto();
                    gameInfoDto.setName(gameInfo.getName());
                    gameInfoDto.setImage(gameInfo.getImage());
                    gameInfoDto.setType(gameInfo.getType());
                    gameInfoDto.setUrl(gameInfo.getUrl());
                    gameInfoDto.setId(gameInfo.getId());
                    return gameInfoDto;
                })
                .toList();
    }
}
