package src.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

import java.io.ByteArrayOutputStream;

import e.wolfsoft1.src.R;
import src.Views.home.HomeView;
import src.Views.menu.PatientMenuView;
import src.Views.profile.PatientProfileView;
import src.customfonts.MyTextView_Roboto_Regular;
import src.domain.PacientProfileDto;
import src.service.impl.PacientProfileServiceMock;
import src.service.interfaces.IProfileService;

public class PatientHomeActivity extends HomeActivity {


    protected PacientProfileDto profile;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PatientHomeActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        IProfileService profileService = new PacientProfileServiceMock();
        Intent intent = getIntent();
        String userKey = "";
        try{
            userKey = intent.getExtras().get("UserKey").toString();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        PacientProfileDto profile = profileService.getProfile(userKey);
        this.profile = profile;

        HomeView patientHomeView = new HomeView(getApplicationContext(), null, profile);
        setContentView(patientHomeView);
    }




}
