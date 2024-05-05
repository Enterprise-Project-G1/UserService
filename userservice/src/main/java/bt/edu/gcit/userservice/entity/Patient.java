package bt.edu.gcit.userservice.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import bt.edu.gcit.userservice.entity.AuthenticationType;

@Entity
@Table(name = "patients")

public class Patient {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "gender", nullable = false)
    private String gender;
    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;
    @Column(name = "date_of_birth",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;
    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "authentication_type", nullable = false, length = 10)
    private AuthenticationType authenticationType;

    @PrePersist
    protected void onCreate() {
        createdTime = new Date();
    }

    public AuthenticationType getAuthenticationType() {
        return authenticationType;
    }

    public void setAuthenticationType(AuthenticationType authenticationType) {
        this.authenticationType = authenticationType;
    }

    public Patient(){

    }

    public Patient(String email, String password, String name, Long phoneNumber, String gender, Date dateOfBirth) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber=phoneNumber;
        this.gender=gender;
        this.dateOfBirth=dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

   

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
