package com.livecode.insurance.treatmentOfInsurance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livecode.insurance.insurance.Insurance;
import com.livecode.insurance.treatment.Treatment;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TreatmentOfInsuranceService implements ITreatmentOfInsuranceService {
    @Autowired
    private TreatmentOfInsuranceRepository treatmentOfInsuranceRepository;

    @Override
    public List<TreatmentOfInsurance> getAllTreatmentInsurances() {
        return treatmentOfInsuranceRepository.findAll().stream()
                .filter(TreatmentInsurance -> !TreatmentInsurance.getIsDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public TreatmentOfInsurance getTreatmentInsuranceById(Long id) {
        TreatmentOfInsurance treatmentInsurance = treatmentOfInsuranceRepository.findById(id).orElse(null);
        if (treatmentInsurance != null && !treatmentInsurance.getIsDeleted()) {
            return treatmentInsurance;
        }
        return null; 
    }

    @Override
    public List<Treatment> getTreatmentsByInsuranceId(UUID insuranceId) {
        List<TreatmentOfInsurance> treatmentOfInsurances = treatmentOfInsuranceRepository.findByInsurance_IdAndIsDeletedFalse(insuranceId);

        List<Treatment> treatments = treatmentOfInsurances.stream()
                .flatMap(treatmentOfInsurance -> treatmentOfInsurance.getTreatments().stream())
                .collect(Collectors.toList());

        if (treatments.isEmpty()) {
            return null;
        }
        return treatments;
    }

   @Override
    public TreatmentOfInsurance saveTreatmentsInsurance(Insurance insurance, List<Treatment> treatments) {
        TreatmentOfInsurance treatmentOfInsurance = new TreatmentOfInsurance(insurance, treatments);

        return treatmentOfInsuranceRepository.save(treatmentOfInsurance);
    }

    @Override
    public void deleteTreatmentInsurance(Long id) {
        TreatmentOfInsurance treatmentInsurance = getTreatmentInsuranceById(id);
        if (treatmentInsurance != null) {
            treatmentInsurance.setIsDeleted(true); 
            treatmentOfInsuranceRepository.save(treatmentInsurance);
        }
    }
}
