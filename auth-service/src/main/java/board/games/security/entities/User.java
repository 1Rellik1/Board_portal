package board.games.security.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;

import static board.games.security.entities.User.TABLE_NAME;

@Table(name = TABLE_NAME)
@Entity
public class User {

    public static final String TABLE_NAME = "userstable";

    // Имя пользователя
    @Column(name = "name")
    @NotNull
    private String userName;

    //Пароль пользователя
    @Column(name = "pwd")
    @NotNull
    private String userPassword;

    // Идентификатор пользователя
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Возвращает имя пользователя
     * @return
     * имя пользователя
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Устанавливает имя пользователя
     * @param userName
     * имя пользователя
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Возвращает пароль пользователя
     * @return
     * пароль пользователя
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Устанавливает пароль пользователя
     * @param pwd
     *  пароль пользователя
     */
    public void setUserPassword(String pwd) {
        this.userPassword = pwd;
    }

    /**
     * Возвращает идентификатор пользователя
     * @return
     * идентификатор пользователя
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор пользователя-
     * @param id
     * идентификатор пользователя
     */
    public void setId(int id) {
        this.id = id;
    }

}
