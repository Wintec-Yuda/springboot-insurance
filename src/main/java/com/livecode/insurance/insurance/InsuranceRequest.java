package com.livecode.insurance.insurance;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class InsuranceRequest {
  private UUID userId;
  private Date expiryDate;
}
