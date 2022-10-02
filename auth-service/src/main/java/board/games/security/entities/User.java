package board.games.security.entities;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.UUID;

import static board.games.security.entities.User.TABLE_NAME;

@Table(name = TABLE_NAME)
@Entity
public class User implements UserDetails {

    public static final String TABLE_NAME = "user_table";

    // Имя пользователя
    @Column(name = "user_name")
    @NotNull
    private String userName;

    //Пароль пользователя
    @Column(name = "password")
    @NotNull
    private String password;

    //Почта пользователя
    @Column(name = "email")
    @NotNull
    private String email;

    // Идентификатор пользователя
    @Id
    private String id = UUID.randomUUID().toString();


    /**
     * Устанавливает имя пользователя
     *
     * @param userName имя пользователя
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Возвращает пароль пользователя
     *
     * @return пароль пользователя
     */
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    /**
     * Устанавливает пароль пользователя
     *
     * @param pwd пароль пользователя
     */
    public void setPassword(String pwd) {
        this.password = pwd;
    }

    /**
     * Возвращает идентификатор пользователя
     *
     * @return идентификатор пользователя
     */
    public String getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор пользователя-
     *
     * @param id идентификатор пользователя
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Возвращает почту пользователя
     *
     * @return почта пользователя
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает почту пользователя-
     *
     * @param email почта пользователя
     */
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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
