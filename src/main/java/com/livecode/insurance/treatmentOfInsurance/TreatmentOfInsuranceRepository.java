package com.livecode.insurance.treatmentOfInsurance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TreatmentOfInsuranceRepository extends JpaRepository<TreatmentOfInsurance, Long> {
    List<TreatmentOfInsurance> findByIsDeletedFalse();
    TreatmentOfInsurance findByIdAndIsDeletedFalse(Long id);
    List<TreatmentOfInsurance> findByInsurance_IdAndIsDeletedFalse(UUID insuranceId);
}
