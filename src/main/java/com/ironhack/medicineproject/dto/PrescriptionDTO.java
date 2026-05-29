
package com.ironhack.medicineproject.dto;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "doctorID",
    "patientID",
    "medicineID",
    "description",
    "prescribedDate"
})

public class PrescriptionDTO {

    @JsonProperty("prescriptionID")
    private Long prescriptionID;

    @JsonProperty("doctorID")
    private Long doctorID;

    @JsonProperty("patientID")
    private Long patientID;

    @JsonProperty("medicineID")
    private Long medicineID;

    @JsonProperty("description")
    @NotBlank(message = "Description cannot be empty")
    private String description;

    @JsonProperty("prescribedDate")
    @NotNull(message = "Date cannot be empty")
    private LocalDate prescribedDate;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public PrescriptionDTO(Long doctorID, Long patientID, Long medicineID, String description, LocalDate prescribedDate) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.medicineID = medicineID;
        this.description = description;
        this.prescribedDate = prescribedDate;
    }

    public PrescriptionDTO() {
    }

    public Long getPrescriptionID() {
        return prescriptionID;
    }
    @JsonProperty("doctorID")
    public Long getDoctorID() {
        return doctorID;
    }

    @JsonProperty("doctorID")
    public void setDoctorID(Long doctorID) {
        this.doctorID = doctorID;
    }

    @JsonProperty("patientID")
    public Long getPatientID() {
        return patientID;
    }

    @JsonProperty("patientID")
    public void setPatientID(Long patientID) {
        this.patientID = patientID;
    }

    @JsonProperty("medicineID")
    public Long getMedicineID() {
        return medicineID;
    }

    @JsonProperty("medicineID")
    public void setMedicineID(Long medicineID) {
        this.medicineID = medicineID;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("prescribedDate")
    public LocalDate getPrescribedDate() {
        return prescribedDate;
    }

    @JsonProperty("prescribedDate")
    public void setPrescribedDate(LocalDate prescribedDate) {
        this.prescribedDate = prescribedDate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PrescriptionDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("doctorID");
        sb.append('=');
        sb.append(((this.doctorID == null)?"<null>":this.doctorID));
        sb.append(',');
        sb.append("patientID");
        sb.append('=');
        sb.append(((this.patientID == null)?"<null>":this.patientID));
        sb.append(',');
        sb.append("medicineID");
        sb.append('=');
        sb.append(((this.medicineID == null)?"<null>":this.medicineID));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("prescribedDate");
        sb.append('=');
        sb.append(((this.prescribedDate == null)?"<null>":this.prescribedDate));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }


}
