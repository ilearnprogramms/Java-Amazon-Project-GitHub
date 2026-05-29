package com.ironhack.medicineproject.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import com.ironhack.medicineproject.enums.DoctorateTitles;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "drTitle",
    "drLastName",
    "username",
    "password"
})

public class DoctorDTO {


    @JsonProperty("drTitle")
    @NotNull(message = "Title cannot be empty")
    private DoctorateTitles drTitle;

    @JsonProperty("drLastName")
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String drLastName;

    @JsonProperty("username")
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    private String username;

    @JsonProperty("password")
    @NotBlank(message = "Password cannot be empty")
    private String password;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public DoctorDTO(DoctorateTitles drTitle, String drLastName, String username, String password) {
        this.drTitle = drTitle;
        this.drLastName = drLastName;
        this.username = username;
        this.password = password;
    }

    public DoctorDTO() {
    }

    @JsonProperty("drTitle")
    public DoctorateTitles getDrTitle() {
        return drTitle;
    }

    @JsonProperty("drTitle")
    public void setDrTitle(DoctorateTitles drTitle) {
        this.drTitle = drTitle;
    }

    @JsonProperty("drLastName")
    public String getDrLastName() {
        return drLastName;
    }

    @JsonProperty("drLastName")
    public void setDrLastName(String drLastName) {
        this.drLastName = drLastName;
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
        sb.append(DoctorDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("drTitle");
        sb.append('=');
        sb.append(((this.drTitle == null)?"<null>":this.drTitle));
        sb.append(',');
        sb.append("drLastName");
        sb.append('=');
        sb.append(((this.drLastName == null)?"<null>":this.drLastName));
        sb.append(',');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null)?"<null>":this.username));
        sb.append(',');
        sb.append("password");
        sb.append('=');
        sb.append(((this.password == null)?"<null>":this.password));
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
