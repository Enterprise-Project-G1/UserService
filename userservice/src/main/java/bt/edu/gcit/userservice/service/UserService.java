package bt.edu.gcit.userservice.service;

import bt.edu.gcit.userservice.entity.User;

public interface UserService {
    User save(User user);
    boolean isEmailDuplicate(String email);
    User updateUser(int id, User updatedUser);
    void deleteById(int theId);
    void updateUserEnabledStatus(int id, boolean enabled);

}