package src.Views.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.ByteArrayOutputStream;

import e.wolfsoft1.src.R;
import src.domain.PacientProfileDto;

public class PatientProfileView extends RelativeLayout {

    protected LayoutInflater inflater;

    protected EditText patientName;
    protected EditText patientEmail;
    protected EditText patientPhone;
    protected EditText patientHealthCardNumber;


    protected PacientProfileDto profile;


    @SuppressLint("WrongThread")
    public PatientProfileView(Context context, PacientProfileDto profile) {
        super(context);
        this.profile = profile;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.patient_profile_layout, this, true);
        ImageView image =  findViewById(R.id.patient_profile_image);

        //encode image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.p3);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        //decode base64 string to image
//        imageBytes = Base64.decode(profile.getPhoto(), Base64.DEFAULT);
        imageBytes = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        image.setImageBitmap(decodedImage);

        initEditBoxed();

    }


    private void initEditBoxed() {
        patientName = this.findViewById(R.id.patient_name);
        patientEmail = this.findViewById(R.id.patient_email);
        patientPhone = this.findViewById(R.id.patient_phone);
        patientHealthCardNumber = this.findViewById(R.id.patient_health_card_number);


        patientName.setText(profile.getFirstName() + " " + profile.getLastName());
        patientEmail.setText(profile.getEmail());
        patientPhone.setText(profile.getPhone());
        patientHealthCardNumber.setText(profile.getHealthCardNumber());

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

    class ProfileEditOnClickListener implements OnClickListener {
        private EditText editToBeEnabled;
        boolean isEditable;

        public ProfileEditOnClickListener(EditText editToBeEnabled) {
            this.editToBeEnabled = editToBeEnabled;
            isEditable = false;
        }

        @Override
        public void onClick(View view) {
            isEditable = !isEditable;
            editToBeEnabled.setFocusable(isEditable);
            editToBeEnabled.setClickable(isEditable);
            editToBeEnabled.setTextIsSelectable(isEditable);

            editToBeEnabled.requestFocus();

        }
    };
}
