package bt.edu.gcit.userservice.dao;

import bt.edu.gcit.userservice.entity.Feedback;
import java.util.List;

public interface FeedbackDAO {
    Feedback save(Feedback feedback);

    Feedback findByID(int theId);

    List<Feedback> findAll();

    void deleteById(int theId);

}