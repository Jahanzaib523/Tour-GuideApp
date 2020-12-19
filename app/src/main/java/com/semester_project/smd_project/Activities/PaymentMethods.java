package com.semester_project.smd_project.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.semester_project.smd_project.R;

import java.io.Serializable;

import Models.TripInfo;

public class PaymentMethods extends AppCompatActivity //implements Serializable
{

    ImageView back, card, cash;
    String GUIDERID_1;
    String UID_1;
    private String StartLocation;
    private String EndLocation;
    private String StartDate;
    private String EndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);

        back = findViewById(R.id.barsid);
        card = findViewById(R.id.cardmethod);
        cash = findViewById(R.id.cashmethod);

        UID_1 = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        GUIDERID_1 = getIntent().getStringExtra("GuiderID");
        StartLocation = getIntent().getStringExtra("StartLocation");
        EndLocation = getIntent().getStringExtra("EndLocation");
        StartDate = getIntent().getStringExtra("StartDate");
        EndDate = getIntent().getStringExtra("EndDate");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(getApplicationContext(), TourGuideSelection.class);
                startActivity(inn);
            }
        });

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(getApplicationContext(), PayingbyCreditCard.class);
                PassIntent(inn);
                startActivity(inn);
            }
        });

        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent innn = new Intent(getApplicationContext(), PayingbyCash.class);
                PassIntent(innn);
                startActivity(innn);
            }
        });
    }

    public void PassIntent(Intent in)
    {
        in.putExtra("UID", UID_1);
        in.putExtra("StartLocation", StartLocation);
        in.putExtra("EndLocation", EndLocation);
        in.putExtra("StartDate", StartDate);
        in.putExtra("EndDate", EndDate);
        in.putExtra("GuiderID", GUIDERID_1);
    }
}