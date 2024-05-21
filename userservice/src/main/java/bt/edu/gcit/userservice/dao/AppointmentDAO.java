package bt.edu.gcit.userservice.dao;

import java.util.Date;
import java.util.List;

import bt.edu.gcit.userservice.entity.Appointment;

public interface AppointmentDAO {
    Appointment save(Appointment appointment);
    Appointment findByID(int theId);
    void deleteById(int theId);
    List<Appointment> findAll();
    List<Appointment> findByDate(Date date);
}
