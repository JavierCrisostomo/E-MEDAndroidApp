package src.service.interfaces;

import src.domain.DoctorProfileDto;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public interface IDoctorProfileService extends IProfileService {
    @Override
    DoctorProfileDto getProfile(String userKey);

    void updateProfile(String userKey, ProfileDto dto);

    void createProfile(String userKey, ProfileDto dto);


}
