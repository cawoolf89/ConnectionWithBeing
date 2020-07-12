package com.example.connectionwithbeing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class OthersMenuActivity extends AppCompatActivity {

    private LinearLayout mHomeButtonBar;
    private ImageView mHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_menu);

        mHomeButtonBar = findViewById(R.id.bottomOthersHomeButtonBar);
        mHomeButton = findViewById(R.id.bottomOthersHomeButton);

        mHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent returnHome = new Intent(OthersMenuActivity.this, MainActivity.class);
                startActivity(returnHome);

            }
        });
    }

//    //When back button on actionbar is pressed, returns to the previous activity which has not been destroyed. Needed for Questions.
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
//                super.onBackPressed(); //This replicates the hard back button on the phone. Could just replace this with an Intent
//                break;
//        }
//        return true;
//    }
}
