package src.domain;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Objects;

public class DoctorProfileDto extends ProfileDto {
    private String speciality;

    public DoctorProfileDto() {
    }

    public DoctorProfileDto(String firstName, String lastName, String email, String phone, String photo, String speciality) {
        super(firstName, lastName, email, phone, photo);
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DoctorProfileDto that = (DoctorProfileDto) o;
        return Objects.equals(speciality, that.speciality);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), speciality);
    }
}
