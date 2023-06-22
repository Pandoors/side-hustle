package pl.sidehustle.app.sidehustle.accountManagement.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.sidehustle.app.sidehustle.accountManagement.model.User;
import pl.sidehustle.app.sidehustle.offerManagement.service.OffersService;

import java.util.List;

@Repository
public class UserRepository {

    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @PersistenceContext
    EntityManager entityManager;

     public User getUserByUsername(String username) {
        try {
            return entityManager.createNamedQuery("User.userByUsername", User.class).setParameter("username", username).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
    public User getUserById(Long userId) {
        try {
            return entityManager.createNamedQuery("User.userById", User.class).setParameter("id", userId).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
    public User getUserByMail(String mail) {
        try {
            return entityManager.createNamedQuery("User.userByMail", User.class).setParameter("mail", mail).getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            return null;
        }
    }
    @Transactional
    public void deleteUser(User user){
         entityManager.remove(user);
    }
    @Transactional
    public void addUser(User user){
        entityManager.persist(user);
    }
    @Transactional
    public void mergeUser(User user){
        entityManager.merge(user);
    }
    @Transactional
    public void multiMergeUsers(List<User> users){
        entityManager.merge(users);
    }
}
