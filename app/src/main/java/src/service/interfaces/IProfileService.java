package src.service.interfaces;

import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public interface IProfileService {
    ProfileDto getProfile(String userKey);

    void updateProfile(String userKey, ProfileDto dto);

    void createProfile(String userKey, ProfileDto dto);

}
