package user.info.service.entities;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Table(name = User.TABLE_NAME)
@Entity
public class User {

    public static final String TABLE_NAME = "user_table";

    // Имя пользователя
    @Column(name = "user_name")
    @NotNull
    private String userName;

    //Почта пользователя
    @Column(name = "email")
    @NotNull
    private String email;


    //Уровень доверия к игроку
    @Column(name = "trust_rating")
    @NotNull
    private Integer trustRating = 0;

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


    public String getUserName() {
        return userName;
    }

    public Integer getTrustRating() {
        return trustRating;
    }

    public void setTrustRating(Integer trustRating) {
        this.trustRating = trustRating;
    }
}
