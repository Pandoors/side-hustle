package pl.sidehustle.app.sidehustle.accountManagement.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.sidehustle.app.sidehustle.offerManagement.model.Offer;
import pl.sidehustle.app.sidehustle.offerManagement.model.OfferRealization;

import java.sql.Date;
import java.util.Set;

@ToString
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

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CV cv;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    private Set<OfferRealization> offerRealizations;

    @OneToMany(mappedBy = "ownerId", cascade = CascadeType.ALL)
    private Set<Offer> offers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", insertable = false, updatable = false)
    private Role role;


    public User() {
    }

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
