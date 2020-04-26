package src.domain;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class PatientProfileDto extends ProfileDto {
    private String healthCardNumber;

    public PatientProfileDto() {
    }

    public PatientProfileDto(String firstName, String lastName, String email, String phone, String photo, String healthCardNumber) {
        super(firstName, lastName, email, phone, photo);
        this.healthCardNumber = healthCardNumber;
    }

    public String getHealthCardNumber() {
        return healthCardNumber;
    }

    public void setHealthCardNumber(String healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PatientProfileDto that = (PatientProfileDto) o;
        return Objects.equals(healthCardNumber, that.healthCardNumber);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), healthCardNumber);
    }
}
