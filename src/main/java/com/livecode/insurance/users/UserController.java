package com.livecode.insurance.users;

import com.livecode.insurance.security.JwtUtil;
import com.livecode.insurance.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private IUserService userService;

  @Autowired
  private JwtUtil jwtUtil;

  @GetMapping
  public ResponseEntity<BaseResponseDTO<List<User>>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return ResponseEntity.ok(new BaseResponseDTO<>(200, "Users fetched successfully", users));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseResponseDTO<User>> getUserById(@PathVariable UUID id) {
    User user = userService.getUserById(id);
    return user != null
        ? ResponseEntity.ok(new BaseResponseDTO<>(200, "User fetched successfully", user))
        : ResponseEntity.status(404).body(new BaseResponseDTO<>(404, "User not found", null));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseResponseDTO<User>> updateUser(@PathVariable UUID id,
      @RequestBody UpdateUserRequest updateUserRequest) {
    User updatedUser = userService.updateUserById(id, updateUserRequest);
    return updatedUser != null
        ? ResponseEntity.ok(new BaseResponseDTO<>(200, "User updated successfully", updatedUser))
        : ResponseEntity.status(404).body(new BaseResponseDTO<>(404, "User not found", null));
  }

  @PostMapping
  public ResponseEntity<BaseResponseDTO<User>> saveUser(@RequestBody User user) {
    User savedUser = userService.saveUser(user);
    return ResponseEntity.ok(new BaseResponseDTO<>(201, "User saved successfully", savedUser));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponseDTO<Void>> deleteUser(@PathVariable UUID id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(new BaseResponseDTO<>(200, "User deleted successfully", null));
  }

  @PostMapping("/logout")
  public ResponseEntity<BaseResponseDTO<String>> logout(@RequestHeader("Authorization") String token) {
    String email = jwtUtil.getEmailFromToken(token);

    if (userService.getUserByEmail(email) != null) {
      String jwtToken = token.replace("Bearer ", "");

      if (jwtUtil.validateToken(jwtToken, email)) {
        jwtUtil.invalidateToken(jwtToken);

        return new ResponseEntity<>(new BaseResponseDTO<>(200,"User logged out successfully", null), HttpStatus.OK);
      } else {
        return new ResponseEntity<>(new BaseResponseDTO<>(400, "Invalid token", null), HttpStatus.BAD_REQUEST);
      }
    }
    return new ResponseEntity<>(new BaseResponseDTO<>(404, "User not found", null), HttpStatus.NOT_FOUND);
  }
}
