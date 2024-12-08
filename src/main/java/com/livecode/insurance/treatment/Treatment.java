package com.livecode.insurance.treatment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.livecode.insurance.treatmentOfInsurance.TreatmentOfInsurance;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "treatments")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long price;

    @ManyToMany(mappedBy = "treatments")
    @JsonBackReference
    private List<TreatmentOfInsurance> treatmentOfInsurances;

    private Boolean isDeleted = false;

    public Treatment(String name, Long price) {
        this.name = name;
        this.price = price;
    }
}
