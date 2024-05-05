package bt.edu.gcit.userservice.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails login(String email, String password);
}
