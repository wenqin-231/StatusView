package com.lewis.widget.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Lewis on 2017/10/23.
 * Description: utils for toolbar
 */

public class ToolBarUtils {

    public static float getToolbarHeight(Toolbar toolbar) {
        float toolbarElevation = 0;
        float toolbarTranslationZ = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbarElevation = toolbar.getElevation();
            toolbarTranslationZ = toolbar.getTranslationZ();
        } else {
            // the height of lineView
            toolbarElevation = 4;
        }
        return toolbar.getMeasuredHeight() + toolbarElevation +
                toolbarTranslationZ;
    }

    public static View getLineView(Context context) {
        View lineView = new View(context);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 4);
        lineView.setBackgroundColor(Color.parseColor("#DEDEDE"));
        lineView.setLayoutParams(lp);
        return lineView;
    }
}

