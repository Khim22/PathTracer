package com.example.mydomain.pathtracer;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {

    public static final String TAG ="MainActivity";
    public static final int ERR_DIALOG_REQ =9001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isServicesUp()){
            initActivity();
        }
    }
     private void initActivity(){
         Button startBtn = findViewById(R.id.start_map_btn);
         startBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                 startActivity(intent);
             }
         });
     }

    public boolean isServicesUp(){
        Log.d(TAG, "isServicesUp: checking version...");
        int avail = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
        if(avail == ConnectionResult.SUCCESS){
            Log.d("TAG","isServicesUp: Google Play Services working.");
            return true;
        }
        else if (GoogleApiAvailability.getInstance().isUserResolvableError(avail)){
            Log.d(TAG, "isServicesUp: error occured:");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, avail, ERR_DIALOG_REQ);
            dialog.show();
        }
        else{
            Log.d(TAG, "isServicesUp: error in connection:");
            Toast.makeText(this, "Unable to connect to Google Map",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
