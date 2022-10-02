package board.games.security.base;

import board.games.security.entities.User;
import board.games.security.entities.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Objects.requireNonNull;

@Component
public class UserDeatilsImpl extends User implements UserDetails {
    /**
     * Репозиторий доступа к {@link User}.
     */
    @Autowired
    private transient UserRepository userRepository;

    /**
     * Id пользователя.
     *
     * @see User
     */
    @Nullable
    private int userId;

    /**
     * Пароль пользователя.
     */
    @Nullable
    private String password;

    /**
     * Логин пользователя.
     */
    @Nullable
    private String username;

    /**
     * Инициализация объекта на основе информации о пользователе.
     *
     * @param user
     *            информация о пользователе, на основе которой инициализируется объект
     */
    public void initialize(final User user) {
        this.userId = user.getId();
        this.username = requireNonNull(user.getUserName());
        this.password = user.getUserPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
