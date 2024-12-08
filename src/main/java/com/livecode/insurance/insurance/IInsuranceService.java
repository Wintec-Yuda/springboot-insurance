package com.livecode.insurance.insurance;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IInsuranceService {
    List<Insurance> getAllInsurances(); 
    Insurance getInsuranceById(UUID id); 
    Insurance saveInsurance(Insurance insurance); 
    void deleteInsurance(UUID id);
    void updateInsuranceStatusById(UUID id, InsuranceStatus status);
    Insurance updateInsuranceExpiryDateById(UUID id, Date expiryDate);
}
