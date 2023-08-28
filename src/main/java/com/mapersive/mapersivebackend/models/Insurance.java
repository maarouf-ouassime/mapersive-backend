package com.mapersive.mapersivebackend.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor @FieldDefaults(level = AccessLevel.PRIVATE)
public class Insurance {
    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    Long id;
    @JsonProperty("Policy_id")
    Long policyId;
    @JsonFormat(pattern = "M/d/yyyy")
    @JsonProperty("Date_of_Purchase")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    Date dateOfPurchase;
    @JsonProperty("Customer_id")
    Long customerId;
    @JsonProperty("Fuel")
    String fuel;
    @JsonProperty("VEHICLE_SEGMENT")
    char vehicleSegment;
    @JsonProperty("Premium")
    double premium;
    @JsonProperty("bodily_injury_liability")
    boolean bodilyInjuryLiability;
    @JsonProperty("personal_injury_protection")
    boolean personalInjuryProtection;
    @JsonProperty("property_damage_liability")
    boolean propertyDamageLiability;
    @JsonProperty("collision")
    boolean collision;
    @JsonProperty("comprehensive")
    boolean comprehensive;
    @JsonProperty("Customer_Gender")
    String customerGender;
    @JsonProperty("Customer_Income_group")
    String customerIncomeGroup;
    @JsonProperty("Customer_Region")
    String customerRegion;
    @JsonProperty("Customer_Marital_status")
    boolean customerMaritalStatus;
}
