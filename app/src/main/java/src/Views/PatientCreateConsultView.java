package src.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.HashMap;
import java.util.Map;

import e.wolfsoft1.src.R;
import src.ViewHolders.SymptomViewHolder;

public class PatientCreateConsultView extends ConsultView {
    private TreeNode root;
    private static int sympton_id_counter = 0;
    private Map<Integer, View> selectedSymptoms;

    public TreeNode getSymptomTreeRoot(){
        return root;
    }

    public PatientCreateConsultView(Context context) {
        super(context);
        selectedSymptoms = new HashMap<>();
        LinearLayout symptom_description_layout = findViewById(R.id.symptom_description_layout);
        LinearLayout symptom_tree = findViewById(R.id.symptom_layout);

        LinearLayout menu_container = findViewById(R.id.menu_container);
        PatientMenuView patient_menu = new PatientMenuView(getContext(), null);
        patient_menu.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        menu_container.addView(patient_menu);

//        ((EditText)findViewById(R.id.symptom_description_editbox)).setClickable(false);
//        ((EditText)findViewById(R.id.symptom_description_editbox)).setFocusable(false);

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

        ImageView bg_image = findViewById(R.id.consult_background_image);

        bg_image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 0;

            }
        });
    }

    private TreeNode createSymptomTree(){
        TreeNode root = TreeNode.root();

        TreeNode general = createTreeNode(R.drawable.symptom_category, "General", "General symptoms");
        TreeNode chills = createTreeNode(R.drawable.symptom_category, "Chills", "Short repeatable sensations of coldness");
        TreeNode fever = createTreeNode(R.drawable.symptom_category, "Fever", "High body temperature");
        general.addChildren(chills, fever);

        TreeNode nervous_system = createTreeNode(R.drawable.symptom_category, "Nervous System", "General symptoms");
        TreeNode confusion = createTreeNode(R.drawable.symptom_category, "Confusion", "Sensation of light-headedness");
        TreeNode headache = createTreeNode(R.drawable.symptom_category, "Head ache", "Pain in the head region");
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

        for(TreeNode child: node.getChildren()){
            if(child.isLeaf()){
                if(! ((SymptomViewHolder.IconTreeItem) child.getValue()).text.contains(text)){
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
                no_child_fits = filterSymptonNodeForText(child, text);
            }
        }

        if(no_child_fits){
            if(!node.equals(root) && node.getViewHolder() != null && node.getViewHolder().getView() != null){
                node.getViewHolder().getView().setVisibility(GONE);
            }
        }
        return no_child_fits;
    }
}
