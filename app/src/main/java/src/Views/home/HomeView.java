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
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.io.ByteArrayOutputStream;

import e.wolfsoft1.src.R;
import src.Views.menu.ViewWithMenu;
import src.Views.menu.MenuView;
import src.Views.menu.PatientMenuView;
import src.activities.CommentsEditableConsultActivity;
import src.customfonts.MyTextView_Roboto_Regular;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public abstract class HomeView extends ViewWithMenu {
    protected ProfileDto profile;
    protected LayoutInflater inflater;


    protected String consultStatus[]={"Pending", "In Progress", "Resolved"};
    protected String consultDates[]={"14 Aug 2019","10 Mar 2019","22 Feb 2019"};
    protected String consultSymptoms[]={"Insomnia Headaches Confusion", "Insomnia Headaches Confusion", "Insomnia Headaches Confusion"};


    @SuppressLint("WrongThread")
    public HomeView(Context context, AttributeSet attrs, ProfileDto profile) {
        super(context, attrs);
        this.profile = profile;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    protected TextView createChip(CharSequence symptom_name, Context context) {
        MyTextView_Roboto_Regular chip = new MyTextView_Roboto_Regular(context);
        chip.setText(symptom_name);
        chip.setBackgroundResource(R.drawable.facilities_rect);
//        chip.setPadding(20,20, 20, 20);
//        chip.setBackgroundResource(R.color.colorSecondaryLightBlue);

        TableRow.LayoutParams chip_layout_params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        chip_layout_params.setMargins(10, 10, 10, 10);
        chip.setLayoutParams(chip_layout_params);
        return chip;
    }



    protected View creatConsultCard(String consultStatus, String consultDate, String consultSymptom) {

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
