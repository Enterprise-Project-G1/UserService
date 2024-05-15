
package bt.edu.gcit.userservice.rest;

import bt.edu.gcit.userservice.entity.Patient;
import bt.edu.gcit.userservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import bt.edu.gcit.userservice.entity.AuthenticationType;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/patient")
public class PatientRestController {
    private final PatientService patientService;

    @Autowired
    public PatientRestController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.registerPatient(patient);
    }

    @PostMapping("/sendVerificationEmail")
    public void sendVerificationEmail(@RequestBody String email) {
        // implementation depends on your email service
    }

    @PostMapping("/enable/{id}")
    public void enable(@PathVariable long id) {
        patientService.enable(id);
    }

    @PostMapping("/isEmailUnique")
    public ResponseEntity<Boolean> isEmailUnique(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        boolean isUnique = patientService.isEmailUnique(email);
        return ResponseEntity.ok(isUnique);
    }

    @PostMapping("/isNumberUnique")
    public ResponseEntity<Boolean> isNumberUnique(@RequestBody Map<String, Long> requestBody) {
        Long number = requestBody.get("phoneNumber");
        boolean isUnique = patientService.isNumberUnique(number);
        return ResponseEntity.ok(isUnique);
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable long id) {
        return patientService.getPatientById(id);
    }

    @GetMapping("/all")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PutMapping("/update/{id}")
    public Patient updatePatient(@PathVariable int id, @RequestBody Map<String, Long> body) {
        Long token = body.get("token");
        return patientService.updateToken(id, token);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePatient(@PathVariable long id) {
        patientService.deletePatient(id);
    }

    @PutMapping("/updateAuthenticationType/{patientId}")
    public void updateAuthenticationType(@PathVariable Long patientId, @RequestBody String type) {
        // Convert the String to an AuthenticationType
        AuthenticationType authenticationType;
        try {
            authenticationType = AuthenticationType.valueOf(type);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid authentication type");
        }
        // Pass the AuthenticationType to the updateAuthenticationType method
        patientService.updateAuthenticationType(patientId, authenticationType);
    }
}
