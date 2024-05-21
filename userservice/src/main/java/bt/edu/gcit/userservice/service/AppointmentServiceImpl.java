package bt.edu.gcit.userservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bt.edu.gcit.userservice.dao.AppointmentDAO;
import bt.edu.gcit.userservice.entity.Appointment;
import jakarta.transaction.Transactional;

@Service
public class AppointmentServiceImpl implements AppointmentService{
    private AppointmentDAO appointmentDAO;

    @Autowired
    public AppointmentServiceImpl (AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    @Override
    @Transactional
    public Appointment save(Appointment appointment) {
        return appointmentDAO.save(appointment);
    }

    @Override
    @Transactional
    public Appointment findByID(int theId){
        return appointmentDAO.findByID(theId);
    }

    @Override
    @Transactional
    public List<Appointment> findAll() {
        return appointmentDAO.findAll();
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        appointmentDAO.deleteById(theId);
    }

    @Override
    @Transactional
    public List<Appointment> findByDate(Date date) {
        return appointmentDAO.findByDate(date);
    }
}
