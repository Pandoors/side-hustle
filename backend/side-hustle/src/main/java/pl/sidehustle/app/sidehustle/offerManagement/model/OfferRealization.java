package pl.sidehustle.app.sidehustle.offerManagement.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.sidehustle.app.sidehustle.accountManagement.model.User;

import java.util.Date;


@Entity
@Getter
@Setter
@Table(name = "offer_realizations")
@Access(AccessType.FIELD)
public class OfferRealization {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private OfferRealizationPK id;

    @Column(name = "taken")
    private Date taken;

    @Column(name = "ended")
    private Date ended;

    @Column(name = "active")
    private boolean active = true;

    @ManyToOne
    @JoinColumn(name = "offer_id", insertable = false, updatable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "worker_id", insertable = false, updatable = false)
    private User worker;

    public OfferRealization() {

    }
}
