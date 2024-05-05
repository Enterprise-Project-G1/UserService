package bt.edu.gcit.userservice.dao;

import bt.edu.gcit.userservice.entity.Patient;
import java.util.List;
import bt.edu.gcit.userservice.entity.AuthenticationType;

public interface PatientDAO {
    public Patient findByEMail(String email);

    public void enable(long id);

    Patient registerPatient(Patient patient);

    boolean isEmailUnique(String email);

    // boolean verify(String code);
    Patient getPatientById(long id);

    List<Patient> getAllPatients();

    Patient updatePatient(Patient patient);

    void deletePatient(long id);

    void updateAuthenticationType(Long patientId, AuthenticationType type);

}
