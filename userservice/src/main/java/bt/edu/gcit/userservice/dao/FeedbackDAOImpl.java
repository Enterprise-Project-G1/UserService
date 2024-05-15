package bt.edu.gcit.userservice.dao;

import bt.edu.gcit.userservice.entity.Feedback;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import java.util.List; // Import List class
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class FeedbackDAOImpl implements FeedbackDAO {
    private EntityManager entityManager;

    @Autowired
    public FeedbackDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Feedback save(Feedback feedback) {
        return entityManager.merge(feedback);
    }

    @Override
    public Feedback findByID(int theId) {
        // Implement the logic to find a user by their ID in the database
        // and return the user object
        Feedback feedback = entityManager.find(Feedback.class, theId);
        return feedback;
    }

    @Override
    public List<Feedback> findAll() {
        return entityManager.createQuery("SELECT feedback FROM Feedback feedback", Feedback.class)
                .getResultList();
    }

    @Override
    public void deleteById(int theId) {
        // Implement the logic to delete a user by their ID from the database
        // find user by id
        Feedback feedback = findByID(theId);
        // remove user
        entityManager.remove(feedback);
    }

}
