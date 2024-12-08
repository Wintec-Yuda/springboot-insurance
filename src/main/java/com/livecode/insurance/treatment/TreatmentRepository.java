package com.livecode.insurance.treatment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    List<Treatment> findByIsDeletedFalse();
    List<Treatment> findByIdInAndIsDeletedFalse(List<Long> ids);
}
