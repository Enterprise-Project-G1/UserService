package bt.edu.gcit.userservice.service;

import bt.edu.gcit.userservice.dao.FeedbackDAO;
import bt.edu.gcit.userservice.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    private FeedbackDAO feedbackDAO;

    @Autowired
    public FeedbackServiceImpl(FeedbackDAO feedbackDAO) {
        this.feedbackDAO = feedbackDAO;
    }

    @Override
    @Transactional
    public Feedback save(Feedback feedback) {
        return feedbackDAO.save(feedback);
    }

    @Transactional
    @Override
    public Feedback findByID(int theId) {
        return feedbackDAO.findByID(theId);
    }

    @Override
    @Transactional
    public List<Feedback> findAll() {
        return feedbackDAO.findAll();
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        feedbackDAO.deleteById(theId);
    }
}
