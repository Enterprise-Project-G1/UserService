package bt.edu.gcit.userservice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bt.edu.gcit.userservice.entity.Appointment;
import bt.edu.gcit.userservice.service.AppointmentService;

@RestController
@RequestMapping("/api")
public class AppointmentRestController {
    private AppointmentService appointmentService;

    @Autowired 
    public AppointmentRestController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/appointments")
    public Appointment save(@RequestBody Appointment appointment) {
        return appointmentService.save(appointment);
    }

    @GetMapping("/appointments")
    public List<Appointment> findAll() {
        return appointmentService.findAll();
    }

    @DeleteMapping("/appointments/{id}")
    public void deleteById(@PathVariable int id){
        appointmentService.deleteById(id);
    }
}
