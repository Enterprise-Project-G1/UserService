package bt.edu.gcit.userservice.rest;

import bt.edu.gcit.userservice.entity.Feedback;
import bt.edu.gcit.userservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.*;

@RestController
@RequestMapping("/api")
public class FeedbackRestController {
    private FeedbackService feedbackService;

    @Autowired
    public FeedbackRestController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/feedbacks")
    public Feedback save(@RequestBody Feedback feedback) {
        return feedbackService.save(feedback);
    }

    @GetMapping("/feedbacks/{id}")
    public Feedback findByID(@PathVariable int id) {
        return feedbackService.findByID(id);
    }

    @GetMapping("/feedbacks")
    public List<Feedback> findAll() {
        return feedbackService.findAll();
    }

    @DeleteMapping("/feedbacks/{id}")
    public void deleteFeedback(@PathVariable int id) {
        feedbackService.deleteById(id);
    }
}
