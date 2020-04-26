package src.domain;

import java.util.ArrayList;
import java.util.List;

public class LocalStorage {
    private static String userKey;

    private static ProfileDto currentProfile;

    public static ProfileDto getCurrentProfile() {
        return currentProfile;
    }

    public static void setCurrentProfile(ProfileDto currentProfile) {
        LocalStorage.currentProfile = currentProfile;
    }

    private static List<ConsultDto> patientConsults;

    private static List<ConsultDto> doctorConsults;

    public static List<ConsultDto> getPatientConsults() {
        if(patientConsults == null){
            patientConsults = new ArrayList<>();
            patientConsults.add(new ConsultDto("10 Mar 2019", "Toothache", "InProgress", "My tooth has been bothering me lately. It's an ache that comes from cold food an beverages."));
            patientConsults.add(new ConsultDto("2 Ian 2019", "HeadAche Confusion Insomina", "Accepted", "My head has been bothering me for some days"));
            patientConsults.add(new ConsultDto("4 Dec 2018", "Fever Cough", "Accepted", "I think I caught a cold"));
            patientConsults.get(0).setDoctorId("1");
            patientConsults.get(1).setDoctorId("1");
            patientConsults.get(2).setDoctorId("1");

        }
        return patientConsults;
    }

    public static void setPatientConsults(List<ConsultDto> patientConsults) {
        LocalStorage.patientConsults = patientConsults;
    }

    public static List<ConsultDto> getDoctorConsults() {
        if(doctorConsults == null){
            doctorConsults = new ArrayList<>();
            doctorConsults.add(new ConsultDto("22 Ian 2019", "Backpain", "Pending", "I lifted something heavy yesterday and I think I pulled my back. Not sure, but I think I should consult a doctor"));
            doctorConsults.add(new ConsultDto("12 Nov 2018", "Fever Cough Sneezing Chills", "Pending", "I've been feeling down lately. I think im getting a cold"));

        }
        return doctorConsults;
    }

    public static void setDoctorConsults(List<ConsultDto> doctorConsults) {
        LocalStorage.doctorConsults = doctorConsults;
    }

    public static String getUserKey() {
        return userKey;
    }

    public static void setUserKey(String userKey) {
        LocalStorage.userKey = userKey;
    }
}
