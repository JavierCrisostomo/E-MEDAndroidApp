package src.ViewHolders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.google.android.flexbox.FlexboxLayout;
import com.unnamed.b.atv.model.TreeNode;

import java.util.Map;

import e.wolfsoft1.src.R;
import src.customfonts.MyTextView_Roboto_Regular;

public class SymptomViewHolder extends TreeNode.BaseNodeViewHolder<SymptomViewHolder.IconTreeItem> {
    private Map<Integer, View> selectedSymptoms;

    public SymptomViewHolder(Context context, Map<Integer, View> selectedSymptoms) {
        super(context);
        this.selectedSymptoms = selectedSymptoms;
    }

    @Override
    public View createNodeView(TreeNode node, final IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.symptom_nodeitem, null, false);
        TextView tvValue = (TextView) view.findViewById(R.id.propertyName);
        TextView descr = (TextView) view.findViewById(R.id.description);

        view.findViewById(R.id.symptom_checkbox).setOnClickListener(new SymptomCheckboxOnClickListener(value));

        PorterShapeImageView image = (PorterShapeImageView) view.findViewById(R.id.foodImage);

        tvValue.setText(value.text);
        descr.setText(value.description);
        image.setImageResource(value.icon);

        if(node.isLeaf()){
            view.findViewById(R.id.symptom_checkbox).setVisibility(View.VISIBLE);
            image.setImageResource(R.drawable.point_icon);
        }
        else{
            view.findViewById(R.id.symptom_checkbox).setVisibility(View.INVISIBLE);
        }

        return view;
    }

    public class SymptomCheckboxOnClickListener implements View.OnClickListener {

        private IconTreeItem symptom_node_item;

        public SymptomCheckboxOnClickListener(IconTreeItem symptom_node_item){
            this.symptom_node_item = symptom_node_item;
        }

        @Override
        public void onClick(View view) {
            CharSequence symptom_name = symptom_node_item.text;
            FlexboxLayout selected_symptom_layout = view.getRootView().findViewById(R.id.selected_symptoms_layout);

            if(((CheckBox) view).isChecked()){
                TextView chip = createChip(symptom_name, view.getContext());

                selected_symptom_layout.addView(chip);
                selectedSymptoms.put(symptom_node_item.id, chip);
            }
            else{
                View chip = selectedSymptoms.get(symptom_node_item.id);
                ((FlexboxLayout) chip.getParent()).removeView(chip);
            }
        }
    }

    private TextView createChip(CharSequence symptom_name, Context context) {
        MyTextView_Roboto_Regular chip = new MyTextView_Roboto_Regular(context);
        chip.setText(symptom_name);
        chip.setBackgroundResource(R.drawable.facilities_rect);
//        chip.setPadding(20,20, 20, 20);
//        chip.setBackgroundResource(R.color.colorSecondaryLightBlue);

        TableRow.LayoutParams chip_layout_params = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        chip_layout_params.setMargins(10, 10, 10, 10);
        chip.setLayoutParams(chip_layout_params);
        return chip;
    }

    public static class IconTreeItem {
        public int id;
        public int icon;
        public String text;
        public String description;

        public IconTreeItem(){
        }

        public IconTreeItem(int id, int icon, String text, String description) {
            this.id = id;
            this.icon = icon;
            this.text = text;
            this.description = description;
        }
    }

}