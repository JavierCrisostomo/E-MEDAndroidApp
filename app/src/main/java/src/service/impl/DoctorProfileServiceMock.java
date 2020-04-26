package src.service.impl;

import src.domain.DoctorProfileDto;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;
import src.service.interfaces.IDoctorProfileService;
import src.service.interfaces.IPatientProfileService;

public class DoctorProfileServiceMock implements IDoctorProfileService {
    @Override
    public DoctorProfileDto getProfile(String userKey) {
        return new DoctorProfileDto("Gregory", "House", "gregory.house@gmail.com", "+55424532513", "", "Diagnostician");
    }

    @Override
    public void updateProfile(String userKey, ProfileDto dto) {

    }

    @Override
    public void createProfile(String userKey, ProfileDto dto) {

    }

}
