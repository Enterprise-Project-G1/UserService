package bt.edu.gcit.userservice.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bt.edu.gcit.userservice.dao.UserDAO;
import bt.edu.gcit.userservice.entity.User;
import bt.edu.gcit.userservice.dao.PatientDAO;
import bt.edu.gcit.userservice.entity.Patient;
import java.util.Collections;

import java.util.List;
import java.util.stream.Collectors;
import bt.edu.gcit.userservice.security.DentaluserDetails;

@Service
public class DentalUserDetailsService implements UserDetailsService {
    @Autowired
    private final UserDAO userDAO;
    private final PatientDAO patientDAO;

    @Autowired
    public DentalUserDetailsService(UserDAO userDAO, PatientDAO patientDAO) {
        this.userDAO = userDAO;
        this.patientDAO = patientDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findByEmail(email);
        if (user != null) {
            List<GrantedAuthority> authorities = user.getRoles().stream()
            .map(role -> {
                System.out.println("Role: " + role.getName()); // print out the role  name
                return new SimpleGrantedAuthority(role.getName());
                })
                
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(), authorities);
        }
        Patient patient = patientDAO.findByEMail(email); // try to load a Customer
        // if no User was found
        if (patient != null) {
            // since Customer entities don't have authorities, we just pass an empty
            // list of authorities
            return new org.springframework.security.core.userdetails.User(patient.getEmail(),
                    patient.getPassword(), Collections.emptyList());
        }
        throw new UsernameNotFoundException("User not found with email: " + email);

        // System.out.println("Email: " + email); // print out the email
        // User user = userDAO.findByEmail(email);
        // if (user == null) {
        // throw new UsernameNotFoundException("User not found with email: " +
        // email);
        // }
        // // System.out.println("User: " + user.getEmail()); // print out the user
        // email
        // List<GrantedAuthority> authorities = user.getRoles().stream()
        // .map(role -> {
        // System.out.println("Role: " + role.getName()); // print out the role
        // // name
        // return new SimpleGrantedAuthority(role.getName());
        // })
        // .collect(Collectors.toList());
        // System.out.println("Authorities: " + authorities); // print out the list of
        // // authorities
        // System.out.println("User in loadbyUserna: " + user.getPassword()); // print
        // // out the user email
        // return new
        // org.springframework.security.core.userdetails.User(user.getEmail(),
        // user.getPassword(), authorities);
    }

}
