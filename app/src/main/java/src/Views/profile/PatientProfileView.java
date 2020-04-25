package src.Views.profile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import e.wolfsoft1.src.R;

public class PatientProfileView extends RelativeLayout {

    protected LayoutInflater inflater;

    protected EditText patientName;
    protected EditText patientEmail;
    protected EditText patientPhone;


    public PatientProfileView(Context context) {
        super(context);

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.patient_profile_layout, this, true);



        initEditBoxed();

    }


    private void initEditBoxed() {
        patientName = this.findViewById(R.id.patient_name);
        patientEmail = this.findViewById(R.id.patient_email);
        patientPhone = this.findViewById(R.id.patient_phone);

        patientName.setFocusable(false);
        patientName.setClickable(false);
        patientName.setTextIsSelectable(false);

        patientEmail.setFocusable(false);
        patientEmail.setClickable(false);
        patientEmail.setTextIsSelectable(false);

        patientPhone.setFocusable(false);
        patientPhone.setClickable(false);
        patientPhone.setTextIsSelectable(false);


        (this.findViewById(R.id.profile_name_edit)).setOnClickListener(new ProfileEditOnClickListener(patientName));
        (this.findViewById(R.id.profile_email_edit)).setOnClickListener(new ProfileEditOnClickListener(patientEmail));
        (this.findViewById(R.id.profile_phone_edit)).setOnClickListener(new ProfileEditOnClickListener(patientPhone));

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
