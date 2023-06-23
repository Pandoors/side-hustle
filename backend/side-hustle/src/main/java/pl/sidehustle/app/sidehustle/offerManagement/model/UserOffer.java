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
@NamedQueries(
        @NamedQuery(
                name = "UserOffer.userOfferExists",
                query = "SELECT CASE WHEN COUNT(uo) > 0 THEN TRUE ELSE FALSE END FROM UserOffer uo WHERE uo.id.userId = :userId AND uo.id.offerId = :offerId"
        )
)
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
