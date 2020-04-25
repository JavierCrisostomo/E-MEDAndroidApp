package src.Views;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import android.content.Intent;
import e.wolfsoft1.src.R;
import src.FirstActivity;
import src.HomeActivity;
import src.activities.ConsultActivity;


public class MenuView extends RelativeLayout {

    private LayoutInflater inflater;

    public MenuView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.menu_layout, this, true);

        (this.findViewById(R.id.home_btn)).setOnClickListener(new HomeButtonOnClickListener());
        (this.findViewById(R.id.choose_screen_btn)).setOnClickListener(new ChooseScreenOnClickListener());
        (this.findViewById(R.id.consult_btn)).setOnClickListener(new ConsultButtonOnClickListener());


    }

    class HomeButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getContext(), HomeActivity.class);
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