package pl.sidehustle.app.sidehustle.offerManagement.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.sidehustle.app.sidehustle.accountManagement.model.User;

@Entity
@Getter
@Setter
@Table(name = "user_offer")
@Access(AccessType.FIELD)
public class UserOffer {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private UserOfferPK id;

    @ManyToOne
    @JoinColumn(name = "offer_id", insertable = false, updatable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
}
