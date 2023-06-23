package pl.sidehustle.app.sidehustle.offerManagement.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.sidehustle.app.sidehustle.accountManagement.model.User;
import pl.sidehustle.app.sidehustle.locationsManagement.model.Location;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "offers")
@Access(AccessType.FIELD)
@NamedQueries({
        @NamedQuery(
                name = "Offer.offerById",
                query = "SELECT o FROM Offer o LEFT JOIN FETCH o.location l WHERE o.id = :id"
        ),
        @NamedQuery(
                name = "Offer.offerByOwnerId",
                query = "SELECT o FROM Offer o WHERE o.ownerId = :id"
        ),
        @NamedQuery(
                name = "Offer.offerByLocationId",
                query = "SELECT o FROM Offer o WHERE o.locationId = :id"
        ),
        @NamedQuery(
                name = "Offer.offerList",
                query = "SELECT o FROM Offer o"
        ),
})
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "offer_type")
    private String offerType;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "payment")
    private BigDecimal payment;

    @Column(name = "offer_start")
    private Date offerStart;

    @Column(name = "offer_end")
    private Date offerEnd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", insertable = false, updatable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private User owner;

    @OneToMany(mappedBy = "offer", fetch = FetchType.LAZY)
    private Set<OfferRealization> offerRealizations;

    public Offer() {
    }

    public Offer(String fullName, String offerType, String description, BigDecimal payment, Location location, User owner, Date offerStart, Date offerEnd, Long locationId, Long userId) {
        this.createdAt = new Date();
        this.fullName = fullName;
        this.offerType = offerType;
        this.description = description;
        this.payment = payment;
        this.location = location;
        this.owner = owner;
        this.offerStart = offerStart;
        this.offerEnd = offerEnd;
        this.locationId = locationId;
        this.ownerId = userId;
    }
}
