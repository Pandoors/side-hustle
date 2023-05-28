package pl.sidehustle.app.sidehustle.accountManagement.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@Access(AccessType.FIELD)

@NamedQueries({
        @NamedQuery(
                name = "User.userById",
                query = "SELECT u FROM User u WHERE u.id = :id"
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
    public User() {
    }
}
