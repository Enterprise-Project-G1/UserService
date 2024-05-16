package bt.edu.gcit.userservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.Arrays;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class DentalSecurityConfig {
    public DentalSecurityConfig() {
        System.out.println("DentalSecurityConfig created");
    }

    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        System.out.println("H2i ");
        return new ProviderManager(Arrays.asList(authProvider()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        System.out.println("Hi ");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        System.out.println("UserDetailsService: " +
                userDetailsService.getClass().getName());
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        System.out.println("AuthProvider: " + authProvider.getClass().getName());
        return authProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/logout").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/users/checkDuplicateEmail").hasAuthority("Receptionist")
                .requestMatchers(HttpMethod.PUT, "/api/users/{id}").hasAuthority("Receptionist")
                .requestMatchers(HttpMethod.DELETE,"/api/users/{id}").hasAuthority("Receptionist")
                .requestMatchers(HttpMethod.PUT, "/api/users/{id}/enabled").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/patient/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/patient/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/patient/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/patient/all").permitAll()
                .requestMatchers(HttpMethod.PUT, "/api/patient/update/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/patient/isEmailUnique").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/patient/delete/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/patient//enable/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/feedbacks").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/feedbacks/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/feedbacks").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/feedbacks/{id}").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/appointments").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/appointments/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/appointments").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/appointments/{id}").permitAll()

                )
                .addFilterBefore(jwtRequestFilter,
                        UsernamePasswordAuthenticationFilter.class);
        // disable CSRF
        http.csrf().disable();
        return http.build();
    }
}