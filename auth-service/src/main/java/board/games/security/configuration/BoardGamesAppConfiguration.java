package board.games.security.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataBaseConfig.class,
         SecurityConfig.class})
public class BoardGamesAppConfiguration {
}
