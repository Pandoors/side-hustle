package pl.sidehustle.app.sidehustle.locationsManagement.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import pl.sidehustle.app.sidehustle.locationsManagement.model.Location;
import pl.sidehustle.app.sidehustle.offerManagement.model.Offer;
import pl.sidehustle.app.sidehustle.offerManagement.repository.OfferRepository;

@Repository
public class LocationRepository {

    Logger logger = LoggerFactory.getLogger(OfferRepository.class);

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void addLocation(Location location) {
        entityManager.persist(location);
    }

}
