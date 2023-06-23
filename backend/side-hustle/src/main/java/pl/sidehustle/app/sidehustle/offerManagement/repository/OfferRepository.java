package pl.sidehustle.app.sidehustle.offerManagement.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pl.sidehustle.app.sidehustle.offerManagement.model.Offer;
import pl.sidehustle.app.sidehustle.offerManagement.model.UserOffer;
import pl.sidehustle.app.sidehustle.offerManagement.model.UserOfferPK;

import java.util.List;

@Repository
public class OfferRepository {

    Logger logger = LoggerFactory.getLogger(OfferRepository.class);

    @PersistenceContext
    EntityManager entityManager;

    public List<Offer> getOffers() {
        try {
            return entityManager.createNamedQuery("Offer.offerList", Offer.class).getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
    public List<Offer> getOffersPersonal(Long userId) {
        try {
            return entityManager.createNamedQuery("Offer.offerListPersonal", Offer.class)
                    .setParameter("ownerId", userId)
                    .getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
    public Offer getOfferById(Long id) {
        try {
            return entityManager.createNamedQuery("Offer.offerById", Offer.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    public List<Offer> getOffersByOwnerId(Long id) {
        try {
            return entityManager.createNamedQuery("Offer.offerByOwnerId", Offer.class).setParameter("id", id).getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    public List<Offer> getOffersByLocationId(Long id) {
        try {
            return entityManager.createNamedQuery("Offer.offerByLocationId", Offer.class).setParameter("id", id).getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    public boolean isOfferApplied(Long offerId, Long userId) {
        try {
            return entityManager.createNamedQuery("UserOffer.userOfferExists", Boolean.class).setParameter("userId", userId).setParameter("offerId", offerId).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return false;
        }
    }

    @Transactional
    public void applyToOffer(Long offerId, Long userId) {
        UserOffer userOffer = new UserOffer();
        UserOfferPK userOfferPK = new UserOfferPK(offerId, userId);
        userOffer.setId(userOfferPK);
        entityManager.persist(userOffer);
    }

    @Transactional
    public void deleteOffer(Offer offer) {
        entityManager.remove(offer);
    }

    @Transactional
    public void mergeOffer(Offer offer) {
        entityManager.merge(offer);
    }

    @Transactional
    public void addOffer(Offer offer) {
        entityManager.persist(offer);
    }

    @Transactional
    public void editOffer(Offer offer) {
        entityManager.merge(offer);
    }

}
