package com.livecode.insurance.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByIsDeletedFalse();

    User findByIdAndIsDeletedFalse(UUID id);

    User findByEmailAndIsDeletedFalse(String email);
}
