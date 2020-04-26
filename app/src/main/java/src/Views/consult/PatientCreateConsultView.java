package src.Views.consult;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import e.wolfsoft1.src.R;
import src.ViewHolders.SymptomViewHolder;
import src.Views.menu.PatientMenuView;
import src.activities.PatientHomeActivity;
import src.domain.ConsultDto;
import src.domain.LocalStorage;
import src.domain.PatientProfileDto;
import src.domain.ProfileDto;

public class PatientCreateConsultView extends ConsultView {
    private TreeNode root;
    private static int sympton_id_counter = 0;
    private Map<Integer, View> selectedSymptoms;

    public TreeNode getSymptomTreeRoot(){
        return root;
    }

    public PatientCreateConsultView(Context context, PatientProfileDto profile) {
        super(context, profile);
        selectedSymptoms = new HashMap<>();
        LinearLayout symptom_tree = findViewById(R.id.symptom_tree);

        createMenuView();
        setupProfileInfo();

        root = createSymptomTree();

        AndroidTreeView tView = new AndroidTreeView(getContext(), root);
        tView.setDefaultAnimation(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        symptom_tree.addView(tView.getView());

        SearchView symtpom_search = findViewById(R.id.symptom_search);
        symtpom_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterSymptomForText(s);
                return true;
            }
        });

        findViewById(R.id.comment_section).setVisibility(GONE);
        findViewById(R.id.doctor_details).setVisibility(GONE);
        findViewById(R.id.consult_status).setVisibility(GONE);
        findViewById(R.id.diagnostic_section).setVisibility(GONE);


        findViewById(R.id.submit_consultation_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                createConsultAndGoHome();
            }
        });
    }

    @Override
    public void createMenuView() {
        LinearLayout menu_container = findViewById(R.id.menu_container);
        PatientMenuView patient_menu = new PatientMenuView(getContext(), null, pacient_profile);
        patient_menu.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        menu_container.addView(patient_menu);
    }

    private TreeNode createSymptomTree(){
        TreeNode root = TreeNode.root();

        TreeNode general = createTreeNode(R.drawable.symptom_category, "General", "General symptoms");
        TreeNode chills = createTreeNode(R.drawable.symptom_category, "Chills", "Short repeatable sensations of coldness");
        TreeNode fever = createTreeNode(R.drawable.symptom_category, "Fever", "High body temperature");
        general.addChildren(chills, fever);

        TreeNode nervous_system = createTreeNode(R.drawable.symptom_category, "Nervous System", "General symptoms");
        TreeNode confusion = createTreeNode(R.drawable.symptom_category, "Confusion", "Sensation of light-headedness");
        TreeNode headache = createTreeNode(R.drawable.symptom_category, "HeadAche", "Pain in the head region");
        nervous_system.addChildren(confusion, headache);

        root.addChildren(general, nervous_system);
        return  root;
    }

    private TreeNode createTreeNode(int icon_id, String name, String description){
        SymptomViewHolder.IconTreeItem nodeItem = new SymptomViewHolder.IconTreeItem(sympton_id_counter, icon_id, name, description);
        sympton_id_counter ++;
        TreeNode node = new TreeNode(nodeItem);
        node.setViewHolder(new SymptomViewHolder(getContext(), selectedSymptoms));
        return node;
    }

    public void filterSymptomForText(String text) {
        filterSymptonNodeForText(root, text);
    }

    private boolean filterSymptonNodeForText(TreeNode node, String text){
        boolean no_child_fits = true;
        boolean self_fit = false;

        if(!node.equals(root))
            if(((SymptomViewHolder.IconTreeItem) node.getValue()).text.toLowerCase().contains(text.toLowerCase())){
                node.getViewHolder().getView().setVisibility(VISIBLE);
                self_fit = true;

                TreeNode parent = node;
                while(parent != null){
                    parent.setExpanded(true);
                    parent.getViewHolder().getTreeView().expandNode(parent);
                    if(!parent.equals(root)){
                        parent.getViewHolder().getView().setVisibility(VISIBLE);
                    }
                    parent = parent.getParent();
                }
            }


        for(TreeNode child: node.getChildren()){
            if(child.isLeaf()){
                if(! ((SymptomViewHolder.IconTreeItem) child.getValue()).text.toLowerCase().contains(text.toLowerCase())){
                    child.getViewHolder().getView().setVisibility(GONE);
                }
                else{
                    child.getViewHolder().getView().setVisibility(VISIBLE);
                    no_child_fits = false;

                    TreeNode parent = child.getParent();
                    while(parent != null){
                        parent.setExpanded(true);
                        parent.getViewHolder().getTreeView().expandNode(parent);
                        if(!parent.equals(root)){
                            parent.getViewHolder().getView().setVisibility(VISIBLE);
                        }
                        parent = parent.getParent();
                    }
                }
            }
            else{
                    no_child_fits =  filterSymptonNodeForText(child, text);
            }
        }

        if(no_child_fits && !self_fit){
            if(!node.equals(root) && node.getViewHolder() != null && node.getViewHolder().getView() != null){
                node.getViewHolder().getView().setVisibility(GONE);
            }
        }
        return no_child_fits;
    }

    private void createConsultAndGoHome(){
        android.text.format.DateFormat df = new android.text.format.DateFormat();
        String date_string = (String) df.format("dd MMM yyyy", new Date());

        String symptoms = "";
        for(Integer key: selectedSymptoms.keySet()){
            symptoms += ((TextView) selectedSymptoms.get(key)).getText() + " ";
        }

        String descr = ((EditText) findViewById(R.id.symptom_description_editbox)).getText().toString();
        ConsultDto consultDto = new ConsultDto(date_string, symptoms, "Pending", descr);

        LocalStorage.getPatientConsults().add(0, consultDto);
        LocalStorage.getDoctorConsults().add(0, consultDto);

        Toast toast = Toast.makeText(getContext(), "Consult request succesfully created!", Toast.LENGTH_LONG);
        toast.show();

        Intent intent=new Intent(getContext(), PatientHomeActivity.class);
        getContext().startActivity(intent);
    }
}
