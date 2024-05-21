package bt.edu.gcit.userservice.service;

import java.util.Date;
import java.util.List;

import bt.edu.gcit.userservice.entity.Appointment;

public interface AppointmentService {
    Appointment save(Appointment appointment);
    Appointment findByID(int theId);
    void deleteById(int theId);
    List<Appointment> findAll();
    List<Appointment> findByDate(Date date);
}
