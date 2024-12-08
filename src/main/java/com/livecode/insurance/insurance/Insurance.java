package com.livecode.insurance.insurance;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.livecode.insurance.treatmentOfInsurance.TreatmentOfInsurance;
import com.livecode.insurance.users.User;

@Data
@NoArgsConstructor
@Entity
@Table(name = "insurances")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "insurance", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<TreatmentOfInsurance> treatmentOfInsurances;

    @Column(nullable = false)
    private InsuranceStatus status = InsuranceStatus.CREATED;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    private Boolean isDeleted = false;

    public Insurance(User user, Date expiryDate) {
        this.user = user;
        this.expiryDate = expiryDate;
    }
}
