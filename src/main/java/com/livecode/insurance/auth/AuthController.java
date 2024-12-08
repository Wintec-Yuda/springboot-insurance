package com.livecode.insurance.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.livecode.insurance.users.IUserService;
import com.livecode.insurance.utils.BaseResponseDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<BaseResponseDTO<String>> register(@RequestBody RegisterRequest registerRequest) {
        if (userService.getUserByEmail(registerRequest.getEmail()) != null) {
            return new ResponseEntity<>(new BaseResponseDTO<>(HttpStatus.BAD_REQUEST.value(),
                    "Email is already taken. Please choose another one.", null), HttpStatus.BAD_REQUEST);
        }

        authService.registerUser(registerRequest);
        return new ResponseEntity<>(new BaseResponseDTO<>(HttpStatus.CREATED.value(),
                "User successfully registered.", null), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponseDTO<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        String token = authService.loginUser(loginRequest);
        if (token != null) {
            LoginResponse loginResponse = new LoginResponse(token);
            return ResponseEntity.ok(new BaseResponseDTO<>(HttpStatus.OK.value(),
                    "Login successful.", loginResponse));
        } else {
            return new ResponseEntity<>(new BaseResponseDTO<>(HttpStatus.UNAUTHORIZED.value(),
                    "Invalid credentials. Please check your email and password.", null), HttpStatus.UNAUTHORIZED);
        }
    }
}
