package com.software_engineering.tap.Main_Notifications_Settings;

import android.Manifest;
import android.arch.persistence.room.Room;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.AccountPage.Fragment_Account;
import com.software_engineering.tap.ExplorePage.Fragment_Explore;
import com.software_engineering.tap.R;
import com.software_engineering.tap.TransactionPage.Fragment_Transaction;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private MenuItem prevMenuItem;
    private BottomNavigationViewEx bottomNavigation;
    private ViewPager viewPager;
    private Toolbar topToolbar;
    private static DrawerLayout mDrawerLayout;
    private TextView toolbarTitle;
    private ArrayList<String> pages;
    private static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pages = new ArrayList<>((Arrays.asList("Account", "TAP", "Explore")));

        topToolbar = findViewById(R.id.top_toolbar);
        toolbarTitle = topToolbar.findViewById(R.id.toolbar_title);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.viewpager);
        mDrawerLayout = findViewById(R.id.drawer_layout);


        setupTopNavigation();
        setupViewPager();
        setupBottomNavigation();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);


        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").build();
    }

    private void setupTopNavigation(){
        setSupportActionBar(topToolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_notification);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    private void setupBottomNavigation(){
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigation.setCurrentItem(1);
        bottomNavigation.setLargeTextSize(14);
        bottomNavigation.setIconTintList(1, null);
        bottomNavigation.getIconAt(1).setBackground(getResources().getDrawable(R.drawable.selector_tap));
        bottomNavigation.setIconSizeAt(1, 42, 42);
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Fragment_Account());
        adapter.addFragment(new Fragment_Transaction());
        adapter.addFragment(new Fragment_Explore());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigation.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigation.getMenu().getItem(position);
                toolbarTitle.setText(pages.get(position));

            }


            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_account:
                // User chose the "Notifications" item

                return true;

            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public static void openDrawer(){
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_navigation, menu);
        return true;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_account:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_tap:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_explore:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    public static AppDatabase getDb() {
        return db;
    }

}
