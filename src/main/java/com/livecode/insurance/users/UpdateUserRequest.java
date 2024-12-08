package com.livecode.insurance.users;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserRequest {
  private String name;
  private String address;
  private String phone;
}
