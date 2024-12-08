package com.livecode.insurance.insurance;

import com.livecode.insurance.users.IUserService;
import com.livecode.insurance.users.User;
import com.livecode.insurance.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/insurances")
public class InsuranceController {

    @Autowired
    private IInsuranceService insuranceService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<BaseResponseDTO<List<Insurance>>> getAllInsurances() {
      List<Insurance> insurances = insuranceService.getAllInsurances();
      return ResponseEntity.ok(new BaseResponseDTO<>(200, "Insurances fetched successfully", insurances));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<Insurance>> getInsuranceById(@PathVariable UUID id) {
      Insurance insurance = insuranceService.getInsuranceById(id);
      return insurance != null
      ? ResponseEntity.ok(new BaseResponseDTO<>(200, "Insurance fetched successfully", insurance))
      : ResponseEntity.status(404).body(new BaseResponseDTO<>(404, "Insurance not found", null));
    }
    
    @PostMapping
    public ResponseEntity<BaseResponseDTO<Insurance>> saveInsurance(@RequestBody InsuranceRequest insuranceRequest) {

      User user = userService.getUserById(insuranceRequest.getUserId());
      if (user == null) {
        return ResponseEntity.status(404).body(new BaseResponseDTO<>(404, "User not found", null));
      }

      Insurance insurance = new Insurance(user, insuranceRequest.getExpiryDate());
      Insurance savedInsurance = insuranceService.saveInsurance(insurance);
      return ResponseEntity.ok(new BaseResponseDTO<>(201, "Insurance saved successfully", savedInsurance));
    }

    @PutMapping("/expiry-date/{id}")
    public ResponseEntity<BaseResponseDTO<Insurance>> updateInsuranceExpiryDate(@PathVariable UUID id, @RequestBody UpdateExpiryDateRequest dateRequest) {
      Insurance updatedInsurance = insuranceService.updateInsuranceExpiryDateById(id, dateRequest.getExpiryDate());
      return updatedInsurance != null
      ? ResponseEntity.ok(new BaseResponseDTO<>(200, "Insurance updated successfully", updatedInsurance))
      : ResponseEntity.status(404).body(new BaseResponseDTO<>(404, "Insurance not found", null));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<Void>> deleteInsurance(@PathVariable UUID id) {
        insuranceService.deleteInsurance(id);
        return ResponseEntity.ok(new BaseResponseDTO<>(200, "Insurance deleted successfully", null));
    }
}
