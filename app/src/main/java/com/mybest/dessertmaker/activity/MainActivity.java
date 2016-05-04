package com.mybest.dessertmaker.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.inthecheesefactory.thecheeselibrary.manager.bus.MainBus;
import com.mybest.dessertmaker.R;
import com.mybest.dessertmaker.busevent.BusEventDessert;
import com.mybest.dessertmaker.fragment.MainFragment;
import com.mybest.dessertmaker.fragment.MoreInfoFrament;
import com.mybest.dessertmaker.manager.DessertListManager;
import com.squareup.otto.Subscribe;


public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstances();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer,MainFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout,
                R.string.hello_world,
                R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MainBus.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MainBus.getInstance().unregister(this);
    }


    ///// you want edit
    @Subscribe
    public void busEventReceived(BusEventDessert event){
        DessertListManager.getInstance().setSelectedItem(event.getDao());

        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.moreInfoContainer);

        if(frameLayout == null) {

            //mobile
            Intent intent = new Intent(MainActivity.this,
                    MoreInfoActivity.class);
            startActivity(intent);
        }
        else{
            //Tablet

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.moreInfoContainer,
                    MoreInfoFrament.newInstance())
                    .commit();
        }
    }
}
