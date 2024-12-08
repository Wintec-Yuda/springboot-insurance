package com.livecode.insurance.treatment;

import java.util.List;

public interface ITreatmentService {
    List<Treatment> getAllTreatments(); 
    Treatment getTreatmentById(Long id); 
    List<Treatment> getTreatmentsByIds(List<Long> treatmentIds);
    Treatment saveTreatment(TreatmentRequest treatment);
    void deleteTreatment(Long id);
    Treatment updateTreatment(Long id, TreatmentRequest treatmentRequest);
}
