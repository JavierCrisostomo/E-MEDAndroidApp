package src.Views.menu;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import e.wolfsoft1.src.R;
import src.activities.PatientCreateConsultActivity;
import src.activities.PatientHomeActivity;
import src.activities.PatientProfileActivity;
import src.domain.ProfileDto;

public class DoctorMenuView extends MenuView {
    private ProfileDto profile;

    public DoctorMenuView(Context context, AttributeSet attrs, ProfileDto profile) {
        super(context, attrs);
        this.profile = profile;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.patient_menu_layout, this, true);

        (this.findViewById(R.id.home_btn)).setOnClickListener(new HomeButtonOnClickListener());
        (this.findViewById(R.id.profile_btn)).setOnClickListener(new ProfileButtonOnClickListener());
        (this.findViewById(R.id.add_consult_btn)).setOnClickListener(new ConsultButtonOnClickListener());
    }

    class HomeButtonOnClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getContext(), PatientHomeActivity.class);
            getContext().startActivity(intent);
        }
    }

    class ProfileButtonOnClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getContext(), PatientProfileActivity.class);
            intent.putExtra("PatientProfile", profile);
            getContext().startActivity(intent);
        }
    }

    class ConsultButtonOnClickListener implements OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getContext(), PatientCreateConsultActivity.class);
            intent.putExtra("PatientProfile", profile);
            getContext().startActivity(intent);
        }
    }
}
