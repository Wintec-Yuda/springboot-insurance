package com.livecode.insurance.users;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    List<User> getAllUsers(); 
    User getUserById(UUID id); 
    User getUserByEmail(String email); 
    User saveUser(User user); 
    void deleteUser(UUID id);
    User updateUserById(UUID id, UpdateUserRequest updatedUser);
}
