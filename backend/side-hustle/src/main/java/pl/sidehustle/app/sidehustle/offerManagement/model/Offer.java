package pl.sidehustle.app.sidehustle.offerManagement.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.sidehustle.app.sidehustle.accountManagement.model.User;
import pl.sidehustle.app.sidehustle.locationsManagement.model.Location;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "offers")
@Access(AccessType.FIELD)
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
    private Double payment;

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
}
