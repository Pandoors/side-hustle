package pl.sidehustle.app.sidehustle.accountManagement.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cvs")
@Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(
                name = "CV.cvByUserId",
                query = "SELECT c FROM CV c WHERE c.userId = :id"
        ),
        @NamedQuery(
                name = "CV.cvList",
                query = "SELECT c FROM CV c"
        ),
        @NamedQuery(
                name = "CV.cvByOfferId",
                query = "SELECT c FROM CV c WHERE c.userId IN (SELECT uo.id.userId FROM UserOffer uo WHERE uo.id.offerId = :id)"
        ),
})
public class CV {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "educational_level")
    private String educationalLevel;

    @Column(name = "contact_email")
    private String contactEmail;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public CV() {
    }
}
