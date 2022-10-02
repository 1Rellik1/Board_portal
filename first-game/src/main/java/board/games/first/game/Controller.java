package board.games.first.game;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class Controller {
    @GetMapping(path ="/lol", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getAllUsers(){
        return "mmmmm";

    }
}
