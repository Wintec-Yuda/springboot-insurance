package com.livecode.insurance.treatment;

import com.livecode.insurance.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

    @Autowired
    private ITreatmentService treatmentService;

    @GetMapping
    public ResponseEntity<BaseResponseDTO<List<Treatment>>> getAllTreatments() {
      List<Treatment> treatments = treatmentService.getAllTreatments();
      return ResponseEntity.ok(new BaseResponseDTO<>(200, "Treatments fetched successfully", treatments));
    }
    
    @PostMapping
    public ResponseEntity<BaseResponseDTO<Treatment>> saveTreatment(@RequestBody TreatmentRequest treatmentRequest) {
      Treatment savedTreatment = treatmentService.saveTreatment(treatmentRequest);
      return ResponseEntity.ok(new BaseResponseDTO<>(201, "Treatment saved successfully", savedTreatment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<Treatment>> updateTreatment(@PathVariable Long id, @RequestBody TreatmentRequest treatmentRequest) {
        Treatment updatedTreatment = treatmentService.updateTreatment(id, treatmentRequest);
        return updatedTreatment != null
                ? ResponseEntity.ok(new BaseResponseDTO<>(200, "Treatment updated successfully", updatedTreatment))
                : ResponseEntity.status(404).body(new BaseResponseDTO<>(404, "Treatment not found", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<Void>> deleteTreatment(@PathVariable Long id) {
        treatmentService.deleteTreatment(id);
        return ResponseEntity.ok(new BaseResponseDTO<>(200, "Treatment deleted successfully", null));
    }
}
