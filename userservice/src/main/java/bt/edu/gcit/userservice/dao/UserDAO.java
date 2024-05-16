package bt.edu.gcit.userservice.dao;

import java.util.List;

import bt.edu.gcit.userservice.entity.User;

public interface UserDAO {
    User save(User user);
    User findByEmail(String email);
    User findByID(int theId);
    void deleteById(int theId);
    void updateUserEnabledStatus(int id, boolean enabled);
    List<User> getAllUsers();
}
