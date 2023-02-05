package game.info.service.controller;

import game.info.service.domain.GameInfo;
import game.info.service.dto.GameInfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import game.info.service.service.GameInfoService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class GameInfoController {

    private final GameInfoService gameInfoService;

    public GameInfoController(GameInfoService gameInfoService) {
        this.gameInfoService = gameInfoService;
    }

    @GetMapping("/getGamesInfo")
    public ResponseEntity<List<GameInfoDto>> getAllInfo(){
        return ResponseEntity.ok().body(gameInfoService.getAllGameInfo());
    }

    @PostMapping("/uploadGameInfo")
    public ResponseEntity<GameInfo> uploadGameInfo(@RequestBody GameInfoDto gameInfoDto) {
        return ResponseEntity.ok().body(gameInfoService.uploadGameInfo(gameInfoDto));
    }

    @PostMapping("/updateGameInfo")
    public ResponseEntity<GameInfo> updateGameInfo(@RequestBody GameInfoDto gameInfoDto) {
        return ResponseEntity.ok().body(gameInfoService.updateGameInfo(gameInfoDto));
    }
}
