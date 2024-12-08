package com.livecode.insurance.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findByIsDeletedFalse();
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findByIdAndIsDeletedFalse(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmailAndIsDeletedFalse(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUserById(UUID id, UpdateUserRequest updatedUser) {
        User existingUser = getUserById(id);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setAddress(updatedUser.getAddress());
            existingUser.setPhone(updatedUser.getPhone());
            return userRepository.save(existingUser);
        }
        return null;
    }

    @Override
    public void deleteUser(UUID id) {
        User user = getUserById(id);
        if (user != null) {
            user.setIsDeleted(true);
            userRepository.save(user);
        }
    }
}
