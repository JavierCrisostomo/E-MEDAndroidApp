package src.Views.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import e.wolfsoft1.src.R;
import src.Views.menu.MenuView;
import src.Views.menu.PatientMenuView;
import src.domain.DoctorProfileDto;
import src.domain.LocalStorage;
import src.domain.PatientProfileDto;

public class DoctorHomeView extends HomeView {
    public DoctorHomeView(Context context, AttributeSet attrs, DoctorProfileDto profile) {
        super(context, attrs, profile);
        inflater.inflate(R.layout.activity_doctor_home, this, true);


        ((TextView) findViewById(R.id.doctor_speciality)).setText(profile.getSpeciality());
        ((TextView) findViewById(R.id.doctor_name)).setText(profile.getFirstName() + " " + profile.getLastName());
        ((TextView) findViewById(R.id.doctor_email)).setText(profile.getEmail());
        ((TextView) findViewById(R.id.doctor_phone)).setText(profile.getPhone());

        createMenuView();


        ImageView profile_image = findViewById(R.id.doctor_profile_image);

        createConsultList(LocalStorage.getDoctorConsults());
    }

    @Override
    public void createMenuView() {
        LinearLayout menu_container = findViewById(R.id.menu_container);
        MenuView menu;

        menu = new PatientMenuView(getContext(), null, profile);

        menu.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        menu_container.addView(menu);
    }
}
