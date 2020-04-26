package src.Views.home;

import android.annotation.SuppressLint;
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
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public class PatientHomeView extends HomeView {
    @SuppressLint("WrongThread")
    public PatientHomeView(Context context, AttributeSet attrs, PatientProfileDto profile) {
        super(context, attrs, profile);
        inflater.inflate(R.layout.activity_patient_home, this, true);

        ((TextView) findViewById(R.id.patient_health_card_number)).setText(profile.getHealthCardNumber());
        ((TextView) findViewById(R.id.patient_name)).setText(profile.getFirstName() + " " + profile.getLastName());
        ((TextView) findViewById(R.id.patient_email)).setText(profile.getEmail());
        ((TextView) findViewById(R.id.patient_phone)).setText(profile.getPhone());

        createMenuView();


        ImageView profile_image = findViewById(R.id.patient_profile_image);

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
        profile_image.setImageBitmap(decodedImage);


        createConsultList();

    }

    @SuppressLint("WrongThread")
    @Override
    public void createMenuView() {
        LinearLayout menu_container = findViewById(R.id.menu_container);
        MenuView menu;

        menu = new PatientMenuView(getContext(), null, profile);

        menu.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        menu_container.addView(menu);
    }

    private void createConsultList() {
        LinearLayout consultList = findViewById(R.id.consult_list);

        for(int i = 0; i < consultStatus.length; i++ ){
            View consult = creatConsultCard(consultStatus[i], consultDates[i], consultSymptoms[i]);
            consultList.addView(consult);
        }
    }
}
