package com.example.woopy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.woopy.dummy.DummyContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ItemFragment.OnListFragmentInteractionListener {

    final static String FILE_NAME = "file_name";
    ViewPager viewPager;
    int runsNumber;
    final String HOW_MUCH_STARTS_KEY = "should not see that";
    SharedPreferences sharedPreferences;

    Fragment3 fragment = new Fragment3();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(fragment, "text");
        viewPagerAdapter.addFragment(new ItemFragment(), "item list");
        viewPagerAdapter.addFragment(new Fragment2(), "the 2");

        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLay);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        int defalt = 0;
        runsNumber = sharedPreferences.getInt(HOW_MUCH_STARTS_KEY, defalt);

        View header = navigationView.getHeaderView(0);


        TextView howMuchStartsTV = header.findViewById(R.id.entersNumber);
        if (runsNumber == 0) {
            howMuchStartsTV.setText("Welcome for your first time!");
            Snackbar.make(findViewById(android.R.id.content), "Message for a new user", Snackbar.LENGTH_INDEFINITE).show();
        } else {
            howMuchStartsTV.setText("You did open this app for " + runsNumber + " times! Thanks for your interest =)");
        }
        runsNumber++;

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(HOW_MUCH_STARTS_KEY, runsNumber);
        edit.apply();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_act1) {
            Intent i = new Intent(this, Activity1.class);
            startActivity(i);
        } else if (id == R.id.nav_act2) {
            Intent i = new Intent(this, Activity2.class);
            startActivity(i);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Toast.makeText(this, item.content, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveTextFile(fragment.getText());

    }

    @Override
    protected void onResume() {
        super.onResume();
        fragment.setText(getTextFile());
    }

    public void saveTextFile(String data) {

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(data.getBytes());
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getTextFile() {
        FileInputStream fileInputStream = null;
        String result = "";

        try{
            fileInputStream = openFileInput(FILE_NAME);
            int kamut = fileInputStream.available();
            byte[] buff = new byte[kamut];
            fileInputStream.read(buff);
            result = new String(buff, StandardCharsets.UTF_8);

        } catch (IOException ex){
                ex.printStackTrace();
        } finally {
            try {
                if(fileInputStream != null){
                    fileInputStream.close();
                }
            } catch (IOException ex){

            }
        }


        return result;
    }

}
