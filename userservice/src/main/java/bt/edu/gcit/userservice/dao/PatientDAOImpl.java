package bt.edu.gcit.userservice.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import bt.edu.gcit.userservice.entity.Patient;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import bt.edu.gcit.userservice.entity.AuthenticationType;

@Repository
public class PatientDAOImpl implements PatientDAO {
    private EntityManager entityManager;

    @Autowired
    public PatientDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Patient findByEMail(String email) {
        TypedQuery<Patient> query = entityManager.createQuery("SELECT p FROM Patient p WHERE p.email = :email", Patient.class);
        query.setParameter("email", email);
        List<Patient> patients = query.getResultList();
        if (patients.isEmpty()) {
            return null;
        } else {
            return patients.get(0);
        }
    }

    @Override
    public Patient findByNumber(Long number) {
        TypedQuery<Patient> query = entityManager.createQuery("SELECT p FROM Patient p WHERE p.phoneNumber = :number", Patient.class);
        query.setParameter("number", number);
        List<Patient> patients = query.getResultList();
        if (patients.isEmpty()) {
            return null;
        } else {
            return patients.get(0);
        }
    }

    @Override
    public void enable(long id) {
        Patient patient = entityManager.find(Patient.class, id);
        if (patient != null) {
            patient.setEnabled(true);
            entityManager.merge(patient);
        }
    }

    @Override
    public Patient registerPatient(Patient patient) {

        patient.setAuthenticationType(AuthenticationType.DATABASE);
        entityManager.persist(patient);
        return patient;
        // entityManager.merge(customer);
        // return customer;
    }

    @Override
    public boolean isEmailUnique(String email) {
        TypedQuery<Patient> query = entityManager.createQuery(
                "SELECT p FROM Patient p WHERE p.email = :email", Patient.class);
        query.setParameter("email", email);
        List<Patient> patients = query.getResultList();
        if (patients.isEmpty()) {
            return false; // Email does not exist in the database
        } else {
            return true; // Return the first Patient entity with the given email
        }
    }
    

    @Override
    public Patient getPatientById(long id) {
        return entityManager.find(Patient.class, id);
    }

    @Override
    public List<Patient> getAllPatients() {
        TypedQuery<Patient> query = entityManager.createQuery(
                "SELECT p FROM Patient p", Patient.class);
        return query.getResultList();
    }

    @Override
    public Patient updatePatient(int id, Patient patient) {
        Patient existingPatient = entityManager.find(Patient.class, id);
        if(existingPatient != null) {
            patient.setId(existingPatient.getId());
            return entityManager.merge(patient);
        }
        return null;
    }

    @Override
    public void deletePatient(long id) {
        Patient patient = entityManager.find(Patient.class, id);
        if (patient != null) {
            entityManager.remove(patient);
        }
    }

    @Override
    public void updateAuthenticationType(Long patientId, AuthenticationType type) {
        Patient patient = entityManager.find(Patient.class, patientId);
        if (patient != null) {
            patient.setAuthenticationType(type);
            entityManager.merge(patient);
        }
    }

}
