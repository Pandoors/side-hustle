package pl.sidehustle.app.sidehustle.offerManagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class UserOfferPK implements Serializable {

    @Column(name = "offer_id")
    private Long offerId;

    @Column(name = "user_id")
    private Long userId;

    public UserOfferPK(Long offerId, Long userId) {
        this.offerId = offerId;
        this.userId = userId;
    }
}
