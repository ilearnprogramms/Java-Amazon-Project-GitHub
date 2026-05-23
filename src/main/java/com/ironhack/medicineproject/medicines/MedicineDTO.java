
package com.ironhack.medicineproject.medicines;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "medicineName",
    "medicineCategory"
})

public class MedicineDTO {

    @JsonProperty("medicineName")
    private String medicineName;
    @JsonProperty("medicineCategory")
    private MedicineCategory medicineCategory;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("medicineName")
    public String getMedicineName() {
        return medicineName;
    }

    @JsonProperty("medicineName")
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public MedicineDTO(String medicineName, MedicineCategory medicineCategory){
        this.medicineName = medicineName;
        this.medicineCategory = medicineCategory;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(MedicineDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("medicineName");
        sb.append('=');
        sb.append(((this.medicineName == null)?"<null>":this.medicineName));
        sb.append(',');
        sb.append("medicineCategory");
        sb.append('=');
        sb.append(((this.medicineCategory == null)?"<null>":this.medicineCategory));
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
