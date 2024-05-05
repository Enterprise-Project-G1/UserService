package bt.edu.gcit.userservice.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bt.edu.gcit.userservice.dao.PatientDAO;
import bt.edu.gcit.userservice.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import bt.edu.gcit.userservice.entity.AuthenticationType;
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientDAO patientDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PatientServiceImpl(PatientDAO patientDAO, PasswordEncoder passwordEncoder) {
        this.patientDAO = patientDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Patient findByEMail(String email) {
        return patientDAO.findByEMail(email);
    }

    @Override
    @Transactional
    public void enable(long id) {
        patientDAO.enable(id);
    }

    @Override
    @Transactional
    public Patient registerPatient(Patient patient) {
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        return patientDAO.registerPatient(patient);
    }

    @Override
    @Transactional
    public boolean isEmailUnique(String email) {
        // return patientDAO.isEmailUnique(email);
        Patient patient=patientDAO.findByEMail(email);
        return patient !=null;
    }

    @Override
    @Transactional
    public Patient getPatientById(long id) {
        return patientDAO.getPatientById(id);
    }

    @Override
    @Transactional
    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    @Override
    @Transactional
    public Patient updatePatient(Patient patient) {
        return patientDAO.updatePatient(patient);
    }

    @Override
    @Transactional
    public void deletePatient(long id) {
        patientDAO.deletePatient(id);
    }

    @Override
    @Transactional
    public void updateAuthenticationType(Long patientId, AuthenticationType type) {
        patientDAO.updateAuthenticationType(patientId, type);
    }

    @Override
    @Transactional
    public void addNewPatientUponOAuthLogin(String name, String email, AuthenticationType authenticationType) {
        Patient patient = new Patient();
        patient.setEmail(email);
        patient.setName(name);
        patient.setEnabled(true);
        patient.setCreatedTime(new Date());
        patient.setAuthenticationType(authenticationType);
        // patient.setDateOfBirth(dateOfBirth); // Set the date of birth here
        patient.setPassword(""); // Ensure to set the password or handle it appropriately
        // patient.setPhoneNumber(""); // Set other properties if necessary
        System.out.println("Patient: " + patient);
        patientDAO.registerPatient(patient);
    }
    
    
    
}