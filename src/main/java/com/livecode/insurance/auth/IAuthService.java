package com.livecode.insurance.auth;

public interface IAuthService {
  void registerUser(RegisterRequest registerRequest);
  String loginUser(LoginRequest loginRequest);
}
