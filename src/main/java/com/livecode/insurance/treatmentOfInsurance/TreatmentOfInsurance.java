package com.livecode.insurance.treatmentOfInsurance;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.livecode.insurance.insurance.Insurance;
import com.livecode.insurance.treatment.Treatment;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "treatment_of_insurances")
public class TreatmentOfInsurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "insurance_id", nullable = false)
    @JsonManagedReference
    private Insurance insurance;

    @ManyToMany
    @JoinTable(
        name = "treatment_of_insurance_treatment", // Nama tabel join
        joinColumns = @JoinColumn(name = "treatment_of_insurance_id"),
        inverseJoinColumns = @JoinColumn(name = "treatment_id")
    )
    @JsonManagedReference
    private List<Treatment> treatments;

    private Boolean isDeleted = false;

    public TreatmentOfInsurance(Insurance insurance, List<Treatment> treatments) {
        this.insurance = insurance;
        this.treatments = treatments;
    }
}
