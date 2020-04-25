package src.service.interfaces;

import src.domain.PacientProfileDto;

public interface IProfileService {
    PacientProfileDto getProfile(String userKey);

    void updateProfile(String userKey, PacientProfileDto dto);
}
