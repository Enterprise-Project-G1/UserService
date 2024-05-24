package bt.edu.gcit.userservice.service;

import bt.edu.gcit.userservice.entity.Patient;
import java.util.List;
import bt.edu.gcit.userservice.entity.AuthenticationType;

public interface PatientService {
    public Patient findByEMail(String email);

    public void enable(long id);

    Patient registerPatient(Patient patient);

    boolean isEmailUnique(String email);
    boolean isNumberUnique(Long number);
    boolean isTokenUnique(Long token);
    // boolean verify(String code);
    Patient getPatientById(long id);

    List<Patient> getAllPatients();

    Patient updateToken(int id, Long token);
    Patient disableAccount(int id);
    Patient enableAccount(int id);
    void deletePatient(long id);

    Patient setActive(int id);
    Patient setInactive(int id);

    void updateAuthenticationType(Long patientId, AuthenticationType type);

    void addNewPatientUponOAuthLogin(String name, String email, AuthenticationType authenticationType);

}
