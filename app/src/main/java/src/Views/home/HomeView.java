package src.Views.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.io.ByteArrayOutputStream;

import e.wolfsoft1.src.R;
import src.Views.menu.PatientMenuView;
import src.activities.CommentsEditableConsultActivity;
import src.customfonts.MyTextView_Roboto_Regular;
import src.domain.PacientProfileDto;

public class HomeView extends RelativeLayout {
    private PacientProfileDto profile;
    protected LayoutInflater inflater;


    protected String consultStatus[]={"Pending", "In Progress", "Resolved"};
    protected String consultDates[]={"14 Aug 2019","10 Mar 2019","22 Feb 2019"};
    protected String consultSymptoms[]={"Insomnia Headaches Confusion", "Insomnia Headaches Confusion", "Insomnia Headaches Confusion"};


    @SuppressLint("WrongThread")
    public HomeView(Context context, AttributeSet attrs, PacientProfileDto profile) {
        super(context, attrs);
        this.profile = profile;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_patient_home, this, true);

        createMenuView();

        ((TextView) findViewById(R.id.patient_name)).setText(profile.getFirstName() + " " + profile.getLastName());
        ((TextView) findViewById(R.id.patient_email)).setText(profile.getEmail());
        ((TextView) findViewById(R.id.patient_phone)).setText(profile.getPhone());
        ((TextView) findViewById(R.id.patient_health_card_number)).setText(profile.getHealthCardNumber());

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


        LinearLayout consultList = findViewById(R.id.consult_list);

        for(int i = 0; i < consultStatus.length; i++ ){
            View consult = creatConsultCard(consultStatus[i], consultDates[i], consultSymptoms[i]);
            consultList.addView(consult);
        }
    }

    private TextView createChip(CharSequence symptom_name, Context context) {
        MyTextView_Roboto_Regular chip = new MyTextView_Roboto_Regular(context);
        chip.setText(symptom_name);
        chip.setBackgroundResource(R.drawable.facilities_rect);
        chip.setPadding(20,20, 20, 20);
        chip.setBackgroundResource(R.color.colorSecondaryLightBlue);

        TableRow.LayoutParams chip_layout_params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        chip_layout_params.setMargins(10, 10, 10, 10);
        chip.setLayoutParams(chip_layout_params);
        return chip;
    }

    public void createMenuView() {
        LinearLayout menu_container = findViewById(R.id.menu_container);
        PatientMenuView patient_menu = new PatientMenuView(getContext(), null, profile);
        patient_menu.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        menu_container.addView(patient_menu);
    }

    private View creatConsultCard(String consultStatus, String consultDate, String consultSymptom) {

        View consult = inflater.inflate(R.layout.consultation_card_view, null);

        ((TextView) consult.findViewById(R.id.consult_date_day)).setText(consultDate.split(" ")[0]);
        ((TextView) consult.findViewById(R.id.consult_date_month)).setText(consultDate.split(" ")[1]);
        ((TextView) consult.findViewById(R.id.consult_date_year)).setText(consultDate.split(" ")[2]);

        ((TextView) consult.findViewById(R.id.consult_status)).setText(consultStatus);
        ((TextView) consult.findViewById(R.id.consult_status)).setTextColor(getResources().getColor(R.color.colorStatusPending));

        for(String sym: consultSymptom.split(" ")){
            TextView chip = createChip(sym, getContext());
            ((FlexboxLayout) consult.findViewById(R.id.selected_symptoms_layout)).addView(chip);
        }
        ((TextView) consult.findViewById(R.id.symptom_description)).setText(getResources().getString(R.string.description));

        consult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), CommentsEditableConsultActivity.class);
                intent.putExtra("PatientProfile", profile);
                getContext().startActivity(intent);
            }
        });

        return consult;
    }


}
