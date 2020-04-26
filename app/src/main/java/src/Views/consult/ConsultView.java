package src.Views.consult;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import e.wolfsoft1.src.R;
import src.Views.menu.ViewWithMenu;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public abstract class ConsultView extends ViewWithMenu {

    protected LayoutInflater inflater;
    protected PatientProfileDto pacient_profile;



    public ConsultView(Context context, PatientProfileDto profile) {
        super(context);
        this.pacient_profile = profile;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.consultation_layout, this, true);

//        LinearLayout symptom_description_layout = findViewById(R.id.symptom_description_layout);
//        symptom_description_layout.setVisibility(GONE);

        findViewById(R.id.doctor_details).setVisibility(GONE);
    }

    public abstract void createMenuView();

    @SuppressLint("WrongThread")
    public void setupProfileInfo(){
        ((TextView) findViewById(R.id.patient_name)).setText(pacient_profile.getFirstName() + " " + pacient_profile.getLastName());
        ((TextView) findViewById(R.id.patient_email)).setText(pacient_profile.getEmail());
        ((TextView) findViewById(R.id.patient_phone)).setText(pacient_profile.getPhone());
        ((TextView) findViewById(R.id.patient_health_card_number)).setText(pacient_profile.getHealthCardNumber());

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

    }
}
