package src.Views.consult;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.unnamed.b.atv.model.TreeNode;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import e.wolfsoft1.src.R;
import src.Views.menu.PatientMenuView;
import src.activities.CommentsEditableConsultActivity;
import src.domain.ConsultDto;
import src.domain.DoctorProfileDto;
import src.domain.LocalStorage;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public class CommentsEditableConsultView extends ConsultView {
    private TreeNode root;
    private static int sympton_id_counter = 0;
    private Map<Integer, View> selectedSymptoms;
    protected DoctorProfileDto doctorProfile;
    protected ConsultDto consult;

    public TreeNode getSymptomTreeRoot(){
        return root;
    }

    public CommentsEditableConsultView(Context context, ConsultDto consult, PatientProfileDto profile, DoctorProfileDto doctor_profile) {
        super(context, profile);
        this.consult = consult;
        this.doctorProfile = doctor_profile;
        selectedSymptoms = new HashMap<>();

        createMenuView();

        LinearLayout symptom_tree_layout = findViewById(R.id.symptom_tree);
        symptom_tree_layout.setVisibility(GONE);
        findViewById(R.id.submit_consultation_button).setVisibility(GONE);


        ((EditText)findViewById(R.id.symptom_description_editbox)).setClickable(false);
        ((EditText)findViewById(R.id.symptom_description_editbox)).setFocusable(false);
        setupProfileInfo();
        setupConsultInfo();
        if(doctor_profile != null){
            setupDoctorProfileInfo();
            findViewById(R.id.doctor_details).setVisibility(VISIBLE);
        }
        else{
            findViewById(R.id.doctor_details).setVisibility(GONE);
        }

        findViewById(R.id.add_comment_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = ((EditText) findViewById(R.id.add_comment_edittext)).getText().toString();
                View comment = creatComment(text, LocalStorage.getCurrentProfile());
                ((LinearLayout) findViewById(R.id.comments)).addView(comment);
                ((EditText) findViewById(R.id.add_comment_edittext)).setText("");
                ((EditText) findViewById(R.id.add_comment_edittext)).clearFocus();

            }
        });

    }

    protected void setupConsultInfo() {
        String consultDate = consult.getDate();
        String consultSymptom = consult.getSymptoms();
        String consultStatus = consult.getStatus();
        String consultDescription = consult.getDescription();


        ((TextView) findViewById(R.id.consult_date_day)).setText(consultDate.split(" ")[0]);
        ((TextView) findViewById(R.id.consult_date_month)).setText(consultDate.split(" ")[1]);
        ((TextView) findViewById(R.id.consult_date_year)).setText(consultDate.split(" ")[2]);

        ((TextView) findViewById(R.id.consult_status)).setText(consultStatus);
        ((TextView) findViewById(R.id.consult_status)).setTextColor(getResources().getColor(R.color.colorStatusPending));

        ((TextView) findViewById(R.id.consult_status)).setText(consultStatus);
        if(consultStatus.equals("Pending")){
            ((TextView) findViewById(R.id.consult_status)).setTextColor(getResources().getColor(R.color.colorStatusPending));
        }
        if(consultStatus.equals("InProgress")){
            ((TextView) findViewById(R.id.consult_status)).setTextColor(getResources().getColor(R.color.colorStatusInProgress));
        }
        if(consultStatus.equals("Accepted")){
            ((TextView) findViewById(R.id.consult_status)).setTextColor(getResources().getColor(R.color.colorStatusAccepted));
        }

        ((TextView) findViewById(R.id.symptom_description_editbox)).setText(consultDescription);



        for(String sym: consultSymptom.split(" ")){
            TextView chip = createChip(sym, getContext());
            ((FlexboxLayout) findViewById(R.id.selected_symptoms_layout)).addView(chip);
        }
    }

    public void setupDoctorProfileInfo(){
        ((TextView) findViewById(R.id.doctor_name)).setText(doctorProfile.getFirstName() + " " + doctorProfile.getLastName());
        ((TextView) findViewById(R.id.diagnostic_doctor_name)).setText(doctorProfile.getFirstName() + " " + doctorProfile.getLastName());

        ((TextView) findViewById(R.id.doctor_email)).setText(doctorProfile.getEmail());
        ((TextView) findViewById(R.id.doctor_phone)).setText(doctorProfile.getPhone());
        ((TextView) findViewById(R.id.doctor_speciality)).setText(doctorProfile.getSpeciality());

        ImageView profile_image = findViewById(R.id.doctor_profile_image);
        ImageView diagnostic_profile_image = findViewById(R.id.diagnostic_doctor_profile_image);

    }

    @Override
    public void createMenuView() {
        LinearLayout menu_container = findViewById(R.id.menu_container);
        PatientMenuView patient_menu = new PatientMenuView(getContext(), null, pacient_profile);
        patient_menu.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        menu_container.addView(patient_menu);
    }

    protected View creatComment(String text, ProfileDto profile) {
        View comment = null;
        if(profile instanceof PatientProfileDto){
             comment = inflater.inflate(R.layout.patient_chat_entry, null);

            ((TextView) comment.findViewById(R.id.comment_text)).setText(text);
            ((TextView) comment.findViewById(R.id.patient_name)).setText(profile.getFirstName() + " " + profile.getLastName());

            android.text.format.DateFormat df = new android.text.format.DateFormat();
            String comment_time = (String) df.format("hh:mm", new Date());

            ((TextView) comment.findViewById(R.id.comment_time)).setText(comment_time);

        }

        return comment;
    }

}
