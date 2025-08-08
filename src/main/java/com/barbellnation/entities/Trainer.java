package com.barbellnation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "trainer")
public class Trainer extends BaseEntity {

    @Column(name = "years_of_experience", length = 50)
    private int yearsOfExperience;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    @JsonIgnore
    private Package packageId;

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
   
    public Package getPackageId() {
        return packageId;
    }

    public void setPackageId(Package packageId) {
        this.packageId = packageId;
    }
    public void setTrainerId(long trainerId) {
        this.setId(trainerId);  
    }
}
