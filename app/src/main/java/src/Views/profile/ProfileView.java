package src.Views.profile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.ByteArrayOutputStream;

import e.wolfsoft1.src.R;
import src.Views.menu.MenuView;
import src.Views.menu.ViewWithMenu;
import src.Views.menu.PatientMenuView;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public abstract class ProfileView extends ViewWithMenu {

    protected LayoutInflater inflater;

    protected EditText patientName;
    protected EditText patientEmail;
    protected EditText patientPhone;


    protected ProfileDto profile;


    @SuppressLint("WrongThread")
    public ProfileView(Context context, ProfileDto profile) {
        super(context);
        this.profile = profile;

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    protected void initEditBoxed() {



    }

    @Override
    public void createMenuView() {
        LinearLayout menu_container = findViewById(R.id.menu_container);
        MenuView menu;
        if(profile instanceof PatientProfileDto){
            menu = new PatientMenuView(getContext(), null, profile);
        }
        else{
            // Put doctor menu here
            menu = null;
        }
        menu.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        menu_container.addView(menu);
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
    }


}
