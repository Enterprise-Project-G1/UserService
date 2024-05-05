package bt.edu.gcit.userservice.service;

import bt.edu.gcit.userservice.entity.Feedback;
import java.util.List;

public interface FeedbackService {
    Feedback save(Feedback feedback);

    Feedback findByID(int theId);

    List<Feedback> findAll();

    void deleteById(int theId);

}