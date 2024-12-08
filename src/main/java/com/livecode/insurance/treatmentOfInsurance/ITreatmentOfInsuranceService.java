package com.livecode.insurance.treatmentOfInsurance;

import java.util.List;
import java.util.UUID;

import com.livecode.insurance.insurance.Insurance;
import com.livecode.insurance.treatment.Treatment;

public interface ITreatmentOfInsuranceService {
    List<TreatmentOfInsurance> getAllTreatmentInsurances(); 
    TreatmentOfInsurance getTreatmentInsuranceById(Long id);
    TreatmentOfInsurance saveTreatmentsInsurance(Insurance insurance, List<Treatment> treatments); 
    void deleteTreatmentInsurance(Long id); 
    List<Treatment> getTreatmentsByInsuranceId(UUID insuranceId);
}
