package com.mybest.dessertmaker.view;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mybest.dessertmaker.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class DesserListItem extends RelativeLayout {


    private TextView tvName;
    private TextView tvDesciption;
    private ImageView ivImg;


    public DesserListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public DesserListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs);
    }

    public DesserListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs);
    }

    private void initInflate() {
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.dessert_list_item, this);
    }

    private void initInstances() {
        tvName = (TextView) findViewById(R.id.tvName);
        tvDesciption = (TextView)findViewById(R.id.tvDesciption);
        ivImg = (ImageView)findViewById(R.id.ivImg);

    }

    private void initWithAttrs(AttributeSet attrs) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);//control pic and other give live  quv
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width,width);
    }



    public void setTvNameText(String text) {
        tvName.setText(text);
    }

    public void setDesciotionText(String text){
        tvDesciption.setText(text);
    }

    public void setImageUrl(String url){
        //TODO : Load Image

        Glide.with(getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImg);
    }

}
