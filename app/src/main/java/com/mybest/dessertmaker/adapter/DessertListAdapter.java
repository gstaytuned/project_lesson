package com.mybest.dessertmaker.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mybest.dessertmaker.R;
import com.mybest.dessertmaker.dao.DessertItemDao;
import com.mybest.dessertmaker.manager.DessertListManager;
import com.mybest.dessertmaker.view.DesserListItem;

/**
 * Created by worapong on 7/9/2015 AD.
 */
public class DessertListAdapter extends BaseAdapter {

    int lastPosition = -1;
    @Override
    public int getCount() {
        if(DessertListManager.getInstance().getDao() == null
        || DessertListManager.getInstance().getDao().getData() == null)
            return 0;

        return DessertListManager.getInstance().getDao().getData().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
/*
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? 0 : 1;
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // if (getItemViewType(position)==0) {
        DesserListItem item;
        if (convertView != null)
            item = (DesserListItem) convertView;
        else
            item = new DesserListItem(parent.getContext());

        ////Fill data
        DessertItemDao dao = DessertListManager.getInstance().getDao().getData().get(position);
        item.setTvNameText(dao.getName());
        item.setDesciotionText(dao.getDescription());
        item.setImageUrl(dao.getImageUrl());

        if(position > lastPosition) {
            Animation anim = AnimationUtils.loadAnimation(parent.getContext(), R.anim.up_from_bottom);
            item.startAnimation(anim);
            lastPosition = position;
        }
        ////
        return item;
      /*  } else {
            TextView item;
            if (convertView != null)
                item = (TextView) convertView;
            else
                item = new TextView(parent.getContext());

            item.setText("Position" + position);
            return item;
        }*/
    }
}
