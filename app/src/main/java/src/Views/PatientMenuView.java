package src.Views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import e.wolfsoft1.src.R;
import src.FirstActivity;
import src.activities.HomeActivity;
import src.activities.ConsultActivity;
import src.activities.PatientHomeActivity;

public class PatientMenuView extends MenuView {
    public PatientMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.patient_menu_layout, this, true);

        (this.findViewById(R.id.home_btn)).setOnClickListener(new HomeButtonOnClickListener());
        (this.findViewById(R.id.choose_screen_btn)).setOnClickListener(new ChooseScreenOnClickListener());
        (this.findViewById(R.id.add_consult_btn)).setOnClickListener(new ConsultButtonOnClickListener());
    }

    class HomeButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getContext(), PatientHomeActivity.class);
            getContext().startActivity(intent);
        }
    }

    class ChooseScreenOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getContext(), FirstActivity.class);
            getContext().startActivity(intent);
        }
    }

    class ConsultButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getContext(), ConsultActivity.class);
            getContext().startActivity(intent);
        }
    }
}
