package src.domain;

import java.io.Serializable;
import java.util.Objects;

public class ConsultDto implements Serializable {
    private String date;
    private String symptoms;
    private String status;
    private String description;
    private String doctorId;
    private String patientId;


    public ConsultDto() {
    }

    public ConsultDto(String date, String symptoms, String status, String description) {
        this.date = date;
        this.symptoms = symptoms;
        this.status = status;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultDto that = (ConsultDto) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(symptoms, that.symptoms) &&
                Objects.equals(status, that.status) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, symptoms, status, description);
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
