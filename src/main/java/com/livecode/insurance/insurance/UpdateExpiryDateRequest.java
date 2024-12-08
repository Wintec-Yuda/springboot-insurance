package com.livecode.insurance.insurance;

import java.util.Date;

import lombok.Data;

@Data
public class UpdateExpiryDateRequest {
  private Date expiryDate;
}
