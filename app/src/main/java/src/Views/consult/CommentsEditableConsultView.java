package src.Views.consult;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.HashMap;
import java.util.Map;

import e.wolfsoft1.src.R;
import src.ViewHolders.SymptomViewHolder;
import src.Views.menu.PatientMenuView;

public class CommentsEditableConsultView extends ConsultView {
    private TreeNode root;
    private static int sympton_id_counter = 0;
    private Map<Integer, View> selectedSymptoms;

    public TreeNode getSymptomTreeRoot(){
        return root;
    }

    public CommentsEditableConsultView(Context context) {
        super(context);
        selectedSymptoms = new HashMap<>();

        createMenuView();

        LinearLayout symptom_tree_layout = findViewById(R.id.symptom_tree_layout);
        symptom_tree_layout.setVisibility(GONE);
        findViewById(R.id.submit_consultation_button).setVisibility(GONE);


        ((EditText)findViewById(R.id.symptom_description_editbox)).setClickable(false);
        ((EditText)findViewById(R.id.symptom_description_editbox)).setFocusable(false);
    }

    @Override
    public void createMenuView() {
        LinearLayout menu_container = findViewById(R.id.menu_container);
        PatientMenuView patient_menu = new PatientMenuView(getContext(), null);
        patient_menu.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        menu_container.addView(patient_menu);
    }

}
