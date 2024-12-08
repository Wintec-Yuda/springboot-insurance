package com.livecode.insurance.insurance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, UUID> {
    List<Insurance> findByIsDeletedFalse();
    Insurance findByIdAndIsDeletedFalse(UUID id);
    @Query("SELECT i FROM Insurance i WHERE i.expiryDate < :today")
    List<Insurance> findInsurancesByExpiryDateAndStatusBefore(Date today);
}
