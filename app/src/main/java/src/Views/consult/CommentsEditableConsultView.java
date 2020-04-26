package src.Views.consult;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unnamed.b.atv.model.TreeNode;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import e.wolfsoft1.src.R;
import src.Views.menu.PatientMenuView;
import src.domain.DoctorProfileDto;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public class CommentsEditableConsultView extends ConsultView {
    private TreeNode root;
    private static int sympton_id_counter = 0;
    private Map<Integer, View> selectedSymptoms;
    protected DoctorProfileDto doctorProfile;

    public TreeNode getSymptomTreeRoot(){
        return root;
    }

    public CommentsEditableConsultView(Context context, PatientProfileDto profile, DoctorProfileDto doctor_profile) {
        super(context, profile);
        this.doctorProfile = doctor_profile;
        selectedSymptoms = new HashMap<>();

        createMenuView();

        LinearLayout symptom_tree_layout = findViewById(R.id.symptom_tree);
        symptom_tree_layout.setVisibility(GONE);
        findViewById(R.id.submit_consultation_button).setVisibility(GONE);


        ((EditText)findViewById(R.id.symptom_description_editbox)).setClickable(false);
        ((EditText)findViewById(R.id.symptom_description_editbox)).setFocusable(false);
        setupProfileInfo();
        if(doctor_profile != null){
            setupDoctorProfileInfo();
            findViewById(R.id.doctor_details).setVisibility(VISIBLE);
        }
        else{
            findViewById(R.id.doctor_details).setVisibility(GONE);
        }


    }

    public void setupDoctorProfileInfo(){
        ((TextView) findViewById(R.id.doctor_name)).setText(doctorProfile.getFirstName() + " " + doctorProfile.getLastName());
        ((TextView) findViewById(R.id.doctor_email)).setText(doctorProfile.getEmail());
        ((TextView) findViewById(R.id.doctor_phone)).setText(doctorProfile.getPhone());
        ((TextView) findViewById(R.id.doctor_speciality)).setText(doctorProfile.getSpeciality());

        ImageView profile_image = findViewById(R.id.doctor_profile_image);
    }

    @Override
    public void createMenuView() {
        LinearLayout menu_container = findViewById(R.id.menu_container);
        PatientMenuView patient_menu = new PatientMenuView(getContext(), null, pacient_profile);
        patient_menu.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        menu_container.addView(patient_menu);
    }

}
