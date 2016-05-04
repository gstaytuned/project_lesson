package com.mybest.dessertmaker.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mybest.dessertmaker.R;
import com.mybest.dessertmaker.dao.DessertItemDao;
import com.mybest.dessertmaker.manager.DessertListManager;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MoreInfoMainFrament extends Fragment {

    TextView tvName;
    TextView tvDescpittion;
    ImageView ivImg;

    public MoreInfoMainFrament() {
        super();
    }

    public static MoreInfoMainFrament newInstance() {
        MoreInfoMainFrament fragment = new MoreInfoMainFrament();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fagment_more_info_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // init instance with rootView.findViewById here
        //setRetainInstance(true);

        tvName = (TextView)rootView.findViewById(R.id.tvName);
        tvDescpittion = (TextView)rootView.findViewById(R.id.tvDesciption);
        ivImg = (ImageView)rootView.findViewById(R.id.ivImg);

        DessertItemDao dao = DessertListManager.getInstance().getSelectedItem();

        tvName.setText(dao.getName());
        tvDescpittion.setText(dao.getDescription());

        Glide.with(MoreInfoMainFrament.this)
                .load(dao.getImageUrl())
                .into(ivImg);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
