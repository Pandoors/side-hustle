package pl.sidehustle.app.sidehustle.offerManagement.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

    public Offer() {
    }
}
