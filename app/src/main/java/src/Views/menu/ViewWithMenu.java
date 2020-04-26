package src.Views.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import e.wolfsoft1.src.R;
import src.Views.menu.PatientMenuView;

public abstract class ViewWithMenu extends RelativeLayout {
    protected LayoutInflater inflater;

    public ViewWithMenu(Context context) {
        super(context);
    }

    public ViewWithMenu(Context context, LayoutInflater inflater) {
        super(context);
        this.inflater = inflater;
    }

    public ViewWithMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public abstract void createMenuView();
}
