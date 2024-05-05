package bt.edu.gcit.userservice.service;

import bt.edu.gcit.userservice.dao.UserDAO;
import bt.edu.gcit.userservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Lazy;
import bt.edu.gcit.userservice.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.save(user);
    }

    @Override
    public boolean isEmailDuplicate(String email) {
        User user = userDAO.findByEmail(email);
        return user != null;
    }

    @Transactional
    @Override
    public User updateUser(int id, User updatedUser) {
        // First, find the user by ID
        User existingUser = userDAO.findByID(id);
        // If the user doesn't exist, throw UserNotFoundException
        if (existingUser == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        // Update the existing user with the data from updatedUser
        existingUser.setName(updatedUser.getName());
        existingUser.setContact(updatedUser.getContact());
        existingUser.setGender(updatedUser.getGender());
        existingUser.setEmail(updatedUser.getEmail());
        // Check if the password has changed. If it has, encode the new password
        // before saving.
        if (!existingUser.getPassword().equals(updatedUser.getPassword())) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        existingUser.setRoles(updatedUser.getRoles());
        // Save the updated user and return it
        return userDAO.save(existingUser);
    }

    @Transactional
    @Override
    public void deleteById(int theId) {
        userDAO.deleteById(theId);
    }

    @Transactional
    @Override
    public void updateUserEnabledStatus(int id, boolean enabled) {
        userDAO.updateUserEnabledStatus(id, enabled);
    }

}