package com.livecode.insurance.insurance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InsuranceService implements IInsuranceService {
    @Autowired
    private InsuranceRepository insuranceRepository;

    @Override
    public List<Insurance> getAllInsurances() {
        return insuranceRepository.findAll().stream()
                .filter(insurance -> !insurance.getIsDeleted())
                .collect(Collectors.toList());
    }

    @Override
    public Insurance getInsuranceById(UUID id) {
        Insurance insurance = insuranceRepository.findById(id).orElse(null);
        if (insurance != null && !insurance.getIsDeleted()) {
            return insurance;
        }
        return null; 
    }

    @Override
    public Insurance saveInsurance(Insurance insurance) {
        return insuranceRepository.save(insurance);
    }

    @Override
    public void updateInsuranceStatusById(UUID id, InsuranceStatus status) {
        Insurance existingInsurance = getInsuranceById(id);
        if (existingInsurance != null) {
            existingInsurance.setStatus(status);
            insuranceRepository.save(existingInsurance);
        }
    }

    @Override
    public Insurance updateInsuranceExpiryDateById(UUID id, Date expiryDate) {
        Insurance existingInsurance = getInsuranceById(id);
        if (existingInsurance != null) {
            existingInsurance.setExpiryDate(expiryDate);
            insuranceRepository.save(existingInsurance);
            return existingInsurance;
        }
        return null;
    }

    @Override
    public void deleteInsurance(UUID id) {
        Insurance insurance = getInsuranceById(id);
        if (insurance != null) {
            insurance.setIsDeleted(true); 
            insuranceRepository.save(insurance);
        }
    }
}
