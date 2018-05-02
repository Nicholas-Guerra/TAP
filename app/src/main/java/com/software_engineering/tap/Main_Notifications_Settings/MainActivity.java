package com.software_engineering.tap.Main_Notifications_Settings;

import android.Manifest;
import android.app.PendingIntent;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.software_engineering.tap.AccountPage.AppDatabase;
import com.software_engineering.tap.AccountPage.Fragment_Account;
import com.software_engineering.tap.AccountPage.Transaction;
import com.software_engineering.tap.AccountPage.User;
import com.software_engineering.tap.ExplorePage.Fragment_Explore;
import com.software_engineering.tap.R;
import com.software_engineering.tap.TransactionPage.DialogFragment_NFC_Pay;
import com.software_engineering.tap.TransactionPage.DialogFragment_NFC_Request;
import com.software_engineering.tap.TransactionPage.Fragment_Transaction;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements Listener, NfcAdapter.OnNdefPushCompleteCallback,
        NfcAdapter.CreateNdefMessageCallback{

    private MenuItem prevMenuItem;
    private BottomNavigationViewEx bottomNavigation;
    private ViewPager viewPager;
    private Toolbar topToolbar;
    private static DrawerLayout mDrawerLayout;
    private TextView toolbarTitle;
    private ArrayList<String> pages;
    private RecyclerView drawerRecyclerView;
    private boolean isDialogDisplayed = false;
    private static boolean isWrite = false;
    private Fragment account, transaction, explore;

    private NfcAdapter mNfcAdapter;
    private static User user;

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
        drawerRecyclerView = findViewById(R.id.drawer_recyclerView);



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);


        drawerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final RecyclerViewAdpater_Notifications recyclerViewAdpater = new RecyclerViewAdpater_Notifications(this, new ArrayList<Transaction_Notification>());
        drawerRecyclerView.setAdapter(recyclerViewAdpater);

        ViewModel_Transaction_Notification viewModel = ViewModelProviders.of(this).get(ViewModel_Transaction_Notification.class);
        viewModel.getTransactionNotifications().observe(MainActivity.this , new Observer<List<Transaction_Notification>>() {
            @Override
            public void onChanged(@Nullable List<Transaction_Notification> transaction_notificationList) {
                recyclerViewAdpater.addItems(transaction_notificationList);
            }
        });

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                while(user==null) {
                    user = AppDatabase.getInstance(MainActivity.this).userDao().getUser();
                }
            }
        });

        initNFC();

        setupTopNavigation();
        setupViewPager();
        setupBottomNavigation();


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

        account = new Fragment_Account();
        transaction = new Fragment_Transaction();
        explore = new Fragment_Explore();

        adapter.addFragment(account);
        adapter.addFragment(transaction);
        adapter.addFragment(explore);
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

    private void initNFC(){

        //Check if NFC is available on device
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(mNfcAdapter != null) {
            //This will refer back to createNdefMessage for what it will send
            mNfcAdapter.setNdefPushMessageCallback(this, this);

            //This will be called if the message is sent successfully
            mNfcAdapter.setOnNdefPushCompleteCallback(this, this);
        }
    }

    @Override
    public void onDialogDisplayed(boolean writer) {
        if(writer) {

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do this after 0.5s = 500ms
                    mNfcAdapter.invokeBeam(MainActivity.this);
                }
            }, 500);

            isWrite = true;
        }

        isDialogDisplayed = true;
    }

    @Override
    public void onDialogDismissed() {
        if(transaction!=null && isWrite)
            getSupportFragmentManager().beginTransaction().detach(transaction).attach(transaction).commit();

        isDialogDisplayed = false;
        isWrite = false;

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isDialogDisplayed && !isWrite) {
            Intent intent = getIntent();
            if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
                Parcelable[] rawMessages = intent.getParcelableArrayExtra(
                        NfcAdapter.EXTRA_NDEF_MESSAGES);

                NdefMessage message = (NdefMessage) rawMessages[0];
                NdefRecord[] attachedRecords = message.getRecords();

                String receiver = new String(attachedRecords[0].getPayload());
                double amount = ByteBuffer.wrap(attachedRecords[1].getPayload()).getDouble();// only one message transferred

                DialogFragment_NFC_Pay frag = (DialogFragment_NFC_Pay) getSupportFragmentManager().findFragmentByTag("nfc_pay");

                frag.onNfcDetected(receiver, amount);

            }
        }

    }

    @Override
    public void onNewIntent(Intent intent) {
        if(isDialogDisplayed && !isWrite) {
            if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
                Parcelable[] receivedArray = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);


                NdefMessage receivedMessage = (NdefMessage) receivedArray[0];
                NdefRecord[] attachedRecords = receivedMessage.getRecords();


                String receiver = new String(attachedRecords[0].getPayload());
                double amount = ByteBuffer.wrap(attachedRecords[1].getPayload()).getDouble();

                //Make sure we don't pass along our AAR (Android Application Record)
                if (!receiver.equals(getPackageName())) {
                    DialogFragment_NFC_Pay frag = (DialogFragment_NFC_Pay) getSupportFragmentManager().findFragmentByTag("nfc_pay");
                    frag.onNfcDetected(receiver, amount);
                }

            }
        }
    }


    @Override
    public NdefMessage createNdefMessage(NfcEvent nfcEvent) {
        if (isDialogDisplayed && isWrite) {

            DialogFragment_NFC_Request frag = (DialogFragment_NFC_Request) getSupportFragmentManager().findFragmentByTag("nfc_request");
            return frag.onNfcDetected();

        }
        return null;

    }

    @Override
    public void onNdefPushComplete(NfcEvent event) {
        //This is called when the system detects that our NdefMessage was
        //Successfully sent.
        Toast.makeText(this, "Successful Transfer", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public static User getUser(){
        while(user == null){}

        return user;
    }


}
