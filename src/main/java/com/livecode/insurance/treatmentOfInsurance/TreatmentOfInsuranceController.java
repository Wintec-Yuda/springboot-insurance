package com.livecode.insurance.treatmentOfInsurance;

import com.livecode.insurance.insurance.IInsuranceService;
import com.livecode.insurance.insurance.Insurance;
import com.livecode.insurance.insurance.InsuranceStatus;
import com.livecode.insurance.treatment.ITreatmentService;
import com.livecode.insurance.treatment.Treatment;
import com.livecode.insurance.utils.BaseResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/treatment-insurances")
public class TreatmentOfInsuranceController {

    @Autowired
    private ITreatmentOfInsuranceService treatmentOfInsuranceService;

    @Autowired
    private ITreatmentService treatmentService;

    @Autowired
    private IInsuranceService insuranceService;

    @GetMapping
    public ResponseEntity<BaseResponseDTO<List<TreatmentOfInsurance>>> getAllTreatmentInsurances() {
        List<TreatmentOfInsurance> treatmentInsurances = treatmentOfInsuranceService.getAllTreatmentInsurances();
        return ResponseEntity
                .ok(new BaseResponseDTO<>(200, "TreatmentInsurances fetched successfully", treatmentInsurances));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<TreatmentOfInsurance>> tetTreatmentInsuranceById(@PathVariable Long id) {
        TreatmentOfInsurance treatmentInsurance = treatmentOfInsuranceService.getTreatmentInsuranceById(id);
        return treatmentInsurance != null
                ? ResponseEntity
                        .ok(new BaseResponseDTO<>(200, "treatmentInsurance fetched successfully", treatmentInsurance))
                : ResponseEntity.status(404).body(new BaseResponseDTO<>(404, "treatmentInsurance not found", null));
    }

    @GetMapping("/insurance/{insuranceId}")
    public ResponseEntity<BaseResponseDTO<List<Treatment>>> getByInsuranceId(@PathVariable UUID insuranceId) {
        List<Treatment> treatments = treatmentOfInsuranceService.getTreatmentsByInsuranceId(insuranceId);

        return treatments != null
                ? ResponseEntity
                        .ok(new BaseResponseDTO<>(200, "Treatments fetched successfully", treatments))
                : ResponseEntity.status(404).body(new BaseResponseDTO<>(404, "Treatments not found", null));
    }

    @PostMapping
    public ResponseEntity<BaseResponseDTO<TreatmentOfInsurance>> saveTreatmentInsurance(
            @RequestBody TreatmentOfInsuranceRequest treatmentInsuranceRequest) {

        Insurance insurance = insuranceService.getInsuranceById(treatmentInsuranceRequest.getInsuranceId());
        if (insurance == null) {
            return ResponseEntity.status(404)
                    .body(new BaseResponseDTO<>(404, "Insurance not found", null));
        }

        List<Treatment> treatments = treatmentService.getTreatmentsByIds(treatmentInsuranceRequest.getTreatmentIds());
        if (treatments.isEmpty()) {
            return ResponseEntity.status(404)
                    .body(new BaseResponseDTO<>(404, "Treatments not found", null));
        }

        insuranceService.updateInsuranceStatusById(insurance.getId(), InsuranceStatus.ACTIVE);

        TreatmentOfInsurance savedTreatmentInsurance = treatmentOfInsuranceService.saveTreatmentsInsurance(insurance,
                treatments);

        return ResponseEntity
                .ok(new BaseResponseDTO<>(201, "Treatment insurance saved successfully", savedTreatmentInsurance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDTO<Void>> deleteTreatmentInsurance(@PathVariable Long id) {
        treatmentOfInsuranceService.deleteTreatmentInsurance(id);
        return ResponseEntity.ok(new BaseResponseDTO<>(200, "treatmentInsurance deleted successfully", null));
    }
}
