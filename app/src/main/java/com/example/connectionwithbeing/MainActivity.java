package com.example.connectionwithbeing;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Declaring all Views
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private LinearLayout mHomeButtonBar;
    private ImageView mHomeButton, mSelfImageView, mOthersImageView, mSocietyImageView, mNatureImageView;
    private TextView mSelfTextView, mOthersTextView, mSocietyTextView, mNatureTextView;

    //Shared Preferences
    public SharedPreferences mSharedPreferences;
    public static final String userActivityProgress = "exercises_completed";

    public static final String selfProgress = "self_progress";
    public static final String othersProgress = "others_progress";
    public static final String natureProgress = "nature_progress";
    public static final String societyProgress= "society_progress";

    public static int natureCompletedInt, othersCompletedInt, selfCompletedInt, societyCompletedInt;

    boolean HARD_BACK_BUTTON_EXIT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//IMPLEMENTING ALL VIEWS
        mHomeButtonBar = findViewById(R.id.bottomHomeButtonBar); //Used for controlling the functionality of the bottom home button.
        mHomeButton = findViewById(R.id.bottomHomeButton); //The actual button itself.

        mSelfImageView = findViewById(R.id.selfImageView);
        mOthersImageView = findViewById(R.id.othersImageView);
        mSocietyImageView = findViewById(R.id.societyImageView);
        mNatureImageView = findViewById(R.id.natureImageView);

        mSelfTextView = findViewById(R.id.selfCompletedTextView);
        mOthersTextView = findViewById(R.id.othersCompletedTextView);
        mNatureTextView = findViewById(R.id.natureCompletedTextView);
        mSocietyTextView = findViewById(R.id.societyCompletedTextView);


//**************************************************************************************************
//SETTING UP SHARED PREFERENCES

          mSharedPreferences = getApplicationContext().getSharedPreferences(userActivityProgress, MODE_PRIVATE);
          final SharedPreferences.Editor mSharedPreferencesEditor = mSharedPreferences.edit();
          setProgressStars();

//**************************************************************************************************
// ACTION BAR AND NAVIGATION

        //Action bar settings.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Adds functionality back button
        getSupportActionBar().setElevation(0); //Removes shadow on action bar
        getSupportActionBar().setTitle("Connection With..."); //Sets the title to be blank on create.

        //Navigation View Settings
        NavigationView navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this); // Activates the onNavItemSelected to make the items work.

        //Creates the actual menu functionality.
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close); //The toggle is the hamburger.
        mDrawerLayout.addDrawerListener(mToggle); // Listens to the toggle button, which is the hamburger for the nav menu?
        mToggle.syncState();
        mDrawerLayout.closeDrawers();


//**************************************************************************************************
//ON CLICK METHOD FOR HOME ITEMS

        mSelfImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startSelfMenu = new Intent(MainActivity.this, SelfMenuActivity.class);
                startActivity(startSelfMenu);
            }
        });

        mOthersImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startOthersMenu = new Intent(MainActivity.this, OthersMenuActivity.class);
                startActivity(startOthersMenu);
            }
        });

        mNatureImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startNatureMenu = new Intent(MainActivity.this, NatureMenuActivity.class);

//                natureCompletedInt = mSharedPreferences.getInt(natureProgress, natureCompletedInt);
//
//                natureCompletedInt += 1;
//
//                mSharedPreferencesEditor.putInt(natureProgress, natureCompletedInt);
//
//                mSharedPreferencesEditor.commit();

                startActivity(startNatureMenu);
            }
        });

        mSocietyImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startSocietyMenu = new Intent(MainActivity.this, SocietyMenuActivity.class);
                startActivity(startSocietyMenu);
            }
        });

        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, R.string.home_button_toast,Toast.LENGTH_SHORT).show();
            }
        });


        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
            return;
        }

        HARD_BACK_BUTTON_EXIT = true;

    } //End of onCreate()


//**************************************************************************************************
//ACTION BAR AND NAVIGATION METHOD IMPLEMENTATIONS

    //  Creates the right hand menu.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);

        return true;
    }


    //  Method needed for the left hand nav menu as well as setting the item actions for the right hand.
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        if(item.getItemId() == R.id.infoItem) {
            String version = "Current Version: " + BuildConfig.VERSION_NAME;
            Toast.makeText(this, version,Toast.LENGTH_SHORT ).show();

        }

        if(item.getItemId() == R.id.settingsItem) {
            Toast.makeText(this,"Settings go here in a group", Toast.LENGTH_SHORT).show();
        }

        if(item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    //    Makes items clickable and perform actions for nav menu.

    //    Sets a unique action bar color for each exercise.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if(id == R.id.home) {


            mDrawerLayout.closeDrawers();
        }

        if(id == R.id.first_fragment_menu){


            mDrawerLayout.closeDrawers();
        }

        if(id == R.id.second_fragment_menu) {

            mDrawerLayout.closeDrawers();
        }

        return true;
    }

//**************************************************************************************************
//VARIOUS MAIN ACTIVITY METHODS

    public void changeActionBarColor(int color) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(color)));
    }

    public void setProgressStars() {

        int stars = mSharedPreferences.getInt(natureProgress, natureCompletedInt);
        String progressStars = "X "+ stars+"/6";
        Log.i("Prefs", progressStars);

        mNatureTextView = findViewById(R.id.natureCompletedTextView);
        mNatureTextView.setText(progressStars);

    }

    //Exits the app if the back button is pressed from the home screen.
    @Override
    public void onBackPressed()
    {
        if (HARD_BACK_BUTTON_EXIT = true)
                moveTaskToBack(true); // exist app
        else
                finish();
    }


//**************************************************************************************************

}
