package board.games.security.entities;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

import static board.games.security.entities.Role.TABLE_NAME;

@Table(name = TABLE_NAME)
@Entity
public class Role implements GrantedAuthority {

    public static final String TABLE_NAME = "role_table";

    // Имя роли
    @Column(name = "role_name")
    @NotNull
    private String role_name;

    // Идентификатор роли
    @Id
    private String id = UUID.randomUUID().toString();

    @Override
    public String getAuthority() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
