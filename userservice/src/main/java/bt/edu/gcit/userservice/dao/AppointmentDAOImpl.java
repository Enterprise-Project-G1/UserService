package bt.edu.gcit.userservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bt.edu.gcit.userservice.entity.Appointment;
import jakarta.persistence.EntityManager;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO{
    private EntityManager entityManager;

    @Autowired
    public AppointmentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public Appointment save(Appointment appointment){
        return entityManager.merge(appointment);
    }

    @Override
    public Appointment findByID(int theId){
        Appointment appointment = entityManager.find(Appointment.class, theId);
        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        return entityManager.createQuery("SELECT appointment FROM Appointment appointment", Appointment.class).getResultList();
    }

    @Override
    public void deleteById(int theId){
        Appointment appointment = findByID(theId);
        entityManager.remove(appointment);
    }
}
