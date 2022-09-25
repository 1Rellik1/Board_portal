package board.games.configuration;


import board.games.security.config.SecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DataBaseConfig.class,
         SecurityConfig.class})
public class BoardGamesAppConfiguration {
}
