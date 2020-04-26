package src.service.impl;

import e.wolfsoft1.src.R;
import src.domain.PacientProfileDto;
import src.service.interfaces.IProfileService;

public class PacientProfileServiceMock implements IProfileService {
    @Override
    public PacientProfileDto getProfile(String userKey) {
        return new PacientProfileDto("Ioan", "Grozea", "ionut.popescu@gmail.com", "+407401231234", "", "122333444455555");
    }

    @Override
    public void updateProfile(String userKey, PacientProfileDto dto) {
    }
}
