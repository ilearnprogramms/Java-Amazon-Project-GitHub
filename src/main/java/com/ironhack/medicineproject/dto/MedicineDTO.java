
package com.ironhack.medicineproject.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ironhack.medicineproject.enums.MedicineCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "medicineName",
    "medicineCategory",
    "medicineQuantity"
})

public class MedicineDTO {

    @JsonProperty("medicineID")
    private Long medicineID;

    @JsonProperty("medicineName")
    @NotBlank(message = "Medicine cannot be empty")
    @Size(min = 5, max = 30, message = "Medicine name must be between 5 and 30 characters")
    private String medicineName;

    @JsonProperty("medicineCategory")
    @NotNull(message = "Category cannot be empty")
    private MedicineCategory medicineCategory;

    @JsonProperty("medicineQuantity")
    @NotNull(message = "Quantity cannot be zero")
    private Integer medicineQuantity;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public MedicineDTO(String medicineName, MedicineCategory medicineCategory, Integer medicineQuantity) {
        this.medicineName = medicineName;
        this.medicineCategory = medicineCategory;
        this.medicineQuantity = medicineQuantity;
    }

    public MedicineDTO() {
    }

    public Long getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(Long medicineID) {
        this.medicineID = medicineID;
    }



    @JsonProperty("medicineName")
    public String getMedicineName() {
        return medicineName;
    }

    @JsonProperty("medicineName")
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    @JsonProperty("medicineQuantity")
    public Integer getMedicineQuantity() {
        return medicineQuantity;
    }

    @JsonProperty("medicineQuantity")
    public void setMedicineQuantity(Integer medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    @JsonProperty("medicineCategory")
    public MedicineCategory getMedicineCategory() {
        return medicineCategory;
    }

    @JsonProperty("medicineCategory")
    public void setMedicineCategory(MedicineCategory medicineCategory) {
        this.medicineCategory = medicineCategory;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
