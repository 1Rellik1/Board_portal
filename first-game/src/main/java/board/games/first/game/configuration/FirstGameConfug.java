package board.games.first.game.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataBaseConfig.class})
public class FirstGameConfug {
}
