package com.livecode.insurance.treatmentOfInsurance;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class TreatmentOfInsuranceRequest {
  private UUID insuranceId;
  private List<Long> treatmentIds;
}
