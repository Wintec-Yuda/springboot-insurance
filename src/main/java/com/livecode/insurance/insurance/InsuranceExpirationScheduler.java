package com.livecode.insurance.insurance;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;

@Component
public class InsuranceExpirationScheduler {

  @Autowired
  private InsuranceRepository insuranceRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void updateExpiredPolicies() {
        LocalDate today = LocalDate.now();
        List<Insurance> expiredInsurances = insuranceRepository.findInsurancesByExpiryDateAndStatusBefore(Date.from(today.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant()));

        for (Insurance insurance : expiredInsurances) {
            insurance.setStatus(InsuranceStatus.EXPIRED); 
        }

        insuranceRepository.saveAll(expiredInsurances); 
    }
}
