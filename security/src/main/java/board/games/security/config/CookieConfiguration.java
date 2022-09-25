package board.games.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import javax.servlet.ServletContext;
import javax.servlet.SessionCookieConfig;

/**
 * Конфигурация стратегии чтения\записи кук.
 */
@Configuration
public class CookieConfiguration {

    /**
     * Создание бина стратегии чтения\записи кук
     *
     * @param servletContext
     *            servletContext
     * @return стратегия чтения\записи кук
     */
    @Bean
    public CookieSerializer
    createDefaultCookieSerializer(@Nullable @Autowired(required = false) ServletContext servletContext) {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setUseBase64Encoding(false);
        if (servletContext != null) {
            SessionCookieConfig sessionCookieConfig = null;
            try {
                sessionCookieConfig = servletContext.getSessionCookieConfig();
            } catch (UnsupportedOperationException ex) {
                throw new RuntimeException();
            }
            if (sessionCookieConfig != null) {
                if (sessionCookieConfig.getName() != null) {
                    cookieSerializer.setCookieName(sessionCookieConfig.getName());
                }
                if (sessionCookieConfig.getDomain() != null) {
                    cookieSerializer.setDomainName(sessionCookieConfig.getDomain());
                }
                if (sessionCookieConfig.getPath() != null) {
                    cookieSerializer.setCookiePath(sessionCookieConfig.getPath());
                }
                if (sessionCookieConfig.getMaxAge() != -1) {
                    cookieSerializer.setCookieMaxAge(sessionCookieConfig.getMaxAge());
                }
            }
        }

        return cookieSerializer;
    }

}
