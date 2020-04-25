package src.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import e.wolfsoft1.src.R;
import src.ViewHolders.SymptomViewHolder;

public class ConsultView extends RelativeLayout {

    protected LayoutInflater inflater;



    public ConsultView(Context context) {
        super(context);

        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.consultation_layout, this, true);

//        LinearLayout symptom_description_layout = findViewById(R.id.symptom_description_layout);
//        symptom_description_layout.setVisibility(GONE);


    }
}
