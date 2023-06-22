package pl.sidehustle.app.sidehustle.accountManagement.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository {

    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext
    EntityManager entityManager;


}
