package pl.sidehustle.app.sidehustle.offerManagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "offer_realizations")
@Access(AccessType.FIELD)
public class OfferRealization {

    @Id
    private Long offer_id;

    @Id
    private Long worker_id;

    @Column(name = "taken")
    private Date taken;

    @Column(name = "ended")
    private Date ended;

    @Column(name = "active")
    private boolean active = true;

    public OfferRealization() {

    }
}
