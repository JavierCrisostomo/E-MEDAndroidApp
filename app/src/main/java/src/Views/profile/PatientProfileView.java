package src.Views.profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import e.wolfsoft1.src.R;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public class PatientProfileView extends ProfileView {
    protected EditText patientHealthCardNumber;

    public PatientProfileView(Context context, PatientProfileDto profile) {
        super(context, profile);
        inflater.inflate(R.layout.patient_profile_layout, this, true);

        ImageView image =  findViewById(R.id.patient_profile_image);
        createMenuView();

        initEditBoxed();
    }

    @Override
    protected void initEditBoxed(){
        super.initEditBoxed();
        PatientProfileDto patientProfileDto = (PatientProfileDto) this.profile;

        patientName = this.findViewById(R.id.patient_name);
        patientEmail = this.findViewById(R.id.patient_email);
        patientPhone = this.findViewById(R.id.patient_phone);
        patientHealthCardNumber = this.findViewById(R.id.patient_health_card_number);

        patientName.setText(profile.getFirstName() + " " + profile.getLastName());
        patientEmail.setText(profile.getEmail());
        patientPhone.setText(profile.getPhone());
        patientHealthCardNumber.setText(patientProfileDto.getHealthCardNumber());

        patientName.setFocusable(false);
        patientName.setClickable(false);
        patientName.setTextIsSelectable(false);

        patientEmail.setFocusable(false);
        patientEmail.setClickable(false);
        patientEmail.setTextIsSelectable(false);

        patientPhone.setFocusable(false);
        patientPhone.setClickable(false);
        patientPhone.setTextIsSelectable(false);


        patientHealthCardNumber.setFocusable(false);
        patientHealthCardNumber.setClickable(false);
        patientHealthCardNumber.setTextIsSelectable(false);

        (this.findViewById(R.id.profile_name_edit)).setOnClickListener(new ProfileEditOnClickListener(patientName));
        (this.findViewById(R.id.profile_email_edit)).setOnClickListener(new ProfileEditOnClickListener(patientEmail));
        (this.findViewById(R.id.profile_phone_edit)).setOnClickListener(new ProfileEditOnClickListener(patientPhone));
        (this.findViewById(R.id.patient_health_card_number)).setOnClickListener(new ProfileEditOnClickListener(patientHealthCardNumber));
    }
}
