package com.inthecheesefactory.thecheeselibrary.view;

/**
 * Created by nuuneoi on 1/13/15 AD.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AspectRatioImageButton extends ImageButton {

    public AspectRatioImageButton(Context context) {
        super(context);
    }

    public AspectRatioImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectRatioImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = width * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
        setMeasuredDimension(width, height);
    }
}
