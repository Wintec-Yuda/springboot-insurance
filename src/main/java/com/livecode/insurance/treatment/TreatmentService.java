package com.livecode.insurance.treatment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreatmentService implements ITreatmentService {
    @Autowired
    private TreatmentRepository treatmentRepository;

    @Override
    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll().stream()
                .filter(treatment -> !treatment.getIsDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Treatment getTreatmentById(Long id) {
        Treatment treatment = treatmentRepository.findById(id).orElse(null);
        if (treatment != null && !treatment.getIsDeleted()) {
            return treatment;
        }
        return null; 
    }

    @Override
    public List<Treatment> getTreatmentsByIds(List<Long> treatmentIds) {
        return treatmentRepository.findByIdInAndIsDeletedFalse(treatmentIds);
    }

    @Override
    public Treatment saveTreatment(TreatmentRequest treatmentRequest) {
        Treatment treatment = new Treatment(treatmentRequest.getName(), treatmentRequest.getPrice());

        return treatmentRepository.save(treatment);
    }

    @Override
    public Treatment updateTreatment(Long id, TreatmentRequest treatmentRequest) {
        Treatment treatment = getTreatmentById(id);
        if (treatment != null) {
            treatment.setName(treatmentRequest.getName());
            treatment.setPrice(treatmentRequest.getPrice());
            return treatmentRepository.save(treatment);
        }
        return null; 
    }

    @Override
    public void deleteTreatment(Long id) {
        Treatment treatment = getTreatmentById(id);
        if (treatment != null) {
            treatment.setIsDeleted(true); 
            treatmentRepository.save(treatment);
        }
    }
}
