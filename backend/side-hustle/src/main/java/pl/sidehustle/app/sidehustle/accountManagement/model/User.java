package pl.sidehustle.app.sidehustle.accountManagement.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users")
@Access(AccessType.FIELD)

@NamedQueries({
//        todo take into consideration date of deleting
        @NamedQuery(
                name = "User.userByUsername",
                query = "SELECT u FROM User u WHERE u.username = :username"
        ),
        @NamedQuery(
                name = "User.userByMail",
                query = "SELECT u FROM User u WHERE u.email = :mail"
        )
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    @Column(name = "created_at")
    private Date createdAt;
    public User() {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
