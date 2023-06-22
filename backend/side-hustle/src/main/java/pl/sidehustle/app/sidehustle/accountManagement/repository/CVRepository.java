package pl.sidehustle.app.sidehustle.accountManagement.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pl.sidehustle.app.sidehustle.accountManagement.model.CV;

import java.util.List;

@Repository
public class CVRepository {
    Logger logger = LoggerFactory.getLogger(CVRepository.class);

    @PersistenceContext
    EntityManager entityManager;

    public CV getCVByUserId(Long userId) {
        try {
            return entityManager.createNamedQuery("CV.cvByUserId", CV.class).setParameter("id", userId).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    public List<CV> getCVsFromOffer(Long offerId) {
        try {
            return entityManager.createNamedQuery("CV.cvByOfferId", CV.class).setParameter("id", offerId).getResultList();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }

    @Transactional
    public void deleteCV(CV cv) {
        entityManager.remove(cv);
    }

    @Transactional
    public void addCV(CV cv) {
        entityManager.persist(cv);
    }

    @Transactional
    public void mergeCV(CV cv) {
        entityManager.merge(cv);
    }
}
