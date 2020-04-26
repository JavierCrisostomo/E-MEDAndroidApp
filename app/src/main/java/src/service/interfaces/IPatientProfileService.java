package src.service.interfaces;

import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public interface IPatientProfileService extends IProfileService {
    @Override
    PatientProfileDto getProfile(String userKey);

    void updateProfile(String userKey, ProfileDto dto);

    void createProfile(String userKey, ProfileDto dto);


}
