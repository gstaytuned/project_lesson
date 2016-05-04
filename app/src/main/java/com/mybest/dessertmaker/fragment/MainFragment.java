package com.mybest.dessertmaker.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.inthecheesefactory.thecheeselibrary.manager.bus.MainBus;
import com.mybest.dessertmaker.R;
import com.mybest.dessertmaker.activity.MoreInfoActivity;
import com.mybest.dessertmaker.adapter.DessertListAdapter;
import com.mybest.dessertmaker.busevent.BusEventDessert;
import com.mybest.dessertmaker.dao.DessertItemCollectionDao;
import com.mybest.dessertmaker.dao.DessertItemDao;
import com.mybest.dessertmaker.manager.DessertListManager;
import com.mybest.dessertmaker.manager.http.HTTPManager;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment {

    ListView listView;
    DessertListAdapter listAdapter;

    SwipeRefreshLayout switTorefest;
    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(final View rootView) {
        // init instance with rootView.findViewById here
        //setRetainInstance(true);
        listView = (ListView)rootView.findViewById(R.id.listView);
        listAdapter = new DessertListAdapter();
        listView.setAdapter(listAdapter);

        switTorefest = (SwipeRefreshLayout)rootView.findViewById(R.id.switTorefest);
        switTorefest.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                switTorefest.setEnabled(firstVisibleItem == 0);
            }
        });

        loadData();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"Position" + position,Toast.LENGTH_LONG
                ).show();

                DessertItemDao dao = DessertListManager.getInstance()
                        .getDao().getData().get(position);
                BusEventDessert event = new BusEventDessert(dao);
                MainBus.getInstance().post(event);

            }
        });
    }



    private void loadData() {
        HTTPManager.getInstance().getAPIService().loadDessertList(new Callback<DessertItemCollectionDao>() {
            @Override
            public void success(DessertItemCollectionDao dessertItemCollectionDao, Response response) {
                switTorefest.setRefreshing(false);

                Toast.makeText(Contextor.getInstance().getContext(),
                        dessertItemCollectionDao.getData().get(0).getName(), Toast.LENGTH_LONG).show();

                DessertListManager.getInstance().setDao(dessertItemCollectionDao);
                // Update listView
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {
                switTorefest.setRefreshing(false);
                Toast.makeText(Contextor.getInstance().getContext(),
                       "Failure"+ error.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("Err", error.getMessage());
            }
        });
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
