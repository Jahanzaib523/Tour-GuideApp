package com.semester_project.smd_project.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;
import com.onesignal.OneSignal;
import com.semester_project.smd_project.R;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import Models.TripInfo;

public class PayingbyCash extends AppCompatActivity
{
    Button pay;
    ImageView back;
    private TripInfo trip_Info;
    private ProgressBar progress;
    private FirebaseDatabase mDatabase, mDatabse1;
    private DatabaseReference dbReference, DbReference1;
    private static final String ORDERS = "ORDERS";
    String GUIDERID_3;
    String UID_3;
    private String StartLocation;
    private String EndLocation;
    private String StartDate;
    private String EndDate;
    private EditText payment;
    String UserImage, GuiderImage;
    private static final String ONESIGNAL_APP_ID = "a7a343a0-776f-4206-b1e2-da8c3031aa17";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payingby_cash);

        back = findViewById(R.id.barsid);
        pay = findViewById(R.id.paybycashbtn);
        progress = findViewById(R.id.progressbar9);
        payment = findViewById(R.id.paymentdetails);
        mDatabase = FirebaseDatabase.getInstance();
        UID_3 = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        GUIDERID_3 = getIntent().getStringExtra("GuiderID");
        StartLocation = getIntent().getStringExtra("StartLocation");
        EndLocation = getIntent().getStringExtra("EndLocation");
        StartDate = getIntent().getStringExtra("StartDate");
        EndDate = getIntent().getStringExtra("EndDate");
        dbReference = mDatabase.getReference(ORDERS);
        final String paid = payment.getText().toString().trim();

        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        OneSignal.sendTag("USER_ID", UID_3);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(getApplicationContext(), PaymentMethods.class);
                startActivity(in1);
                finish();
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                TripInfo trip = new TripInfo(UID_3, StartLocation, EndLocation, StartDate, EndDate, GUIDERID_3, "In Progress", payment.getText().toString(), "Sent");
                mDatabase.getReference().child("ORDERS").push().setValue(trip);

                Intent inn1 = new Intent(getApplicationContext(), PaymentSuccessful.class);
                startActivity(inn1);
                finish();
                progress.setVisibility(View.GONE);
            }
        });
    }
}