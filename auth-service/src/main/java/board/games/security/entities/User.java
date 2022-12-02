package board.games.security.entities;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static board.games.security.entities.User.TABLE_NAME;
import static board.games.security.entities.UserToRole.ColumnName.U2RT_ROLE_ID;
import static board.games.security.entities.UserToRole.ColumnName.U2RT_USER_ID;
import static board.games.security.entities.UserToRole.USER_2_ROLE_TABLE_NAME;

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

    @ManyToMany
    @JoinTable(
            name = USER_2_ROLE_TABLE_NAME,
            joinColumns = @JoinColumn(name = U2RT_USER_ID),
            inverseJoinColumns = @JoinColumn(
                    name = U2RT_ROLE_ID))
    private Set<Role> authorities = new HashSet<>();

    /**
     * Флаг, указывающий, активен ли пользователь.
     */
    @Column(nullable = false, name = "active")
    Boolean active = true;

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

    /**
     * Возвращает имя пользователя
     *
     * @return имя пользователя
     */
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

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public String toJsonFormat() {
        return "{\"userName\":\"" + userName + '"' + ',' +
                "\"email\":\""+ email + '"' + ',' +
                "\"id\":\""  + id + "\"}" ;
    }
}
