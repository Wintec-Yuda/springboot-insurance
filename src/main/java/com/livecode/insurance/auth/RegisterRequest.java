package com.livecode.insurance.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String name;
  private String address;
  private String phone;
  private String email;
  private String password;
}
