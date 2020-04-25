package src.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;

import src.Adapters.PropertyRecyclerAdapter;
import src.ModelClasses.PropertyRecyclerModel;
import e.wolfsoft1.src.R;
import src.PropertyDetailsActivity;

public class HomeActivity extends AppCompatActivity {

    protected ArrayList<PropertyRecyclerModel> homeListModelClassArrayList;
    protected RecyclerView recyclerView;
    protected PropertyRecyclerAdapter mAdapter;
    TextView refine;

    protected String propertyName[]={"Property Name","Property Name","Property Name"};
    protected String street1[]={"14 Street/3rd Block","14 Street/3rd Block","14 Street/3rd Block"};
    protected String street2[]={"Super Build-up Area:1060 Sq.Ft","Super Build-up Area:1060 Sq.Ft","Super Build-up Area:1060 Sq.Ft"};
    protected String amount[]={"$2200","$2200","$2200"};
    protected String bedcount[]={"3","3","3"};
    protected String carParking[]={"1","1","1"};
    protected String swimmingpool[]={"1","1","1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        HomeActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

       refine=(TextView)findViewById(R.id.refine);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);


        refine.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(HomeActivity.this, PropertyDetailsActivity.class);
               startActivity(intent);
           }
       });

        homeListModelClassArrayList = new ArrayList<>();

        for (int i = 0; i < propertyName.length; i++) {
            PropertyRecyclerModel beanClassForRecyclerView_contacts = new PropertyRecyclerModel(propertyName[i], street1[i],street2[i],amount[i],bedcount[i],carParking[i],swimmingpool[i]);

            homeListModelClassArrayList.add(beanClassForRecyclerView_contacts);
        }
        mAdapter = new PropertyRecyclerAdapter(HomeActivity.this,homeListModelClassArrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(HomeActivity.this
        );
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }


}