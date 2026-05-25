
package com.ironhack.medicineproject.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ironhack.medicineproject.enums.PatientTitles;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "username",
    "password",
    "patientTitle",
    "patientLastName"
})

public class PatientDTO {

    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    @JsonProperty("patientTitle")
    private PatientTitles patientTitle;
    @JsonProperty("patientLastName")
    private String patientLastName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public PatientDTO(PatientTitles patientTitle, String patientLastName, String username, String password) {
        this.patientTitle = patientTitle;
        this.patientLastName = patientLastName;
        this.username = username;
        this.password = password;
    }

    public PatientDTO() {
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("patientTitle")
    public PatientTitles getPatientTitle() {
        return patientTitle;
    }

    @JsonProperty("patientTitle")
    public void setPatientTitle(PatientTitles patientTitle) {
        this.patientTitle = patientTitle;
    }

    @JsonProperty("patientLastName")
    public String getPatientLastName() {
        return patientLastName;
    }

    @JsonProperty("patientLastName")
    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
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
        sb.append(PatientDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null)?"<null>":this.username));
        sb.append(',');
        sb.append("password");
        sb.append('=');
        sb.append(((this.password == null)?"<null>":this.password));
        sb.append(',');
        sb.append("patientTitle");
        sb.append('=');
        sb.append(((this.patientTitle == null)?"<null>":this.patientTitle));
        sb.append(',');
        sb.append("patientLastName");
        sb.append('=');
        sb.append(((this.patientLastName == null)?"<null>":this.patientLastName));
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
