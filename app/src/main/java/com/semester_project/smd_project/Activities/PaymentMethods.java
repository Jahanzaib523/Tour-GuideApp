package com.semester_project.smd_project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.semester_project.smd_project.R;

public class PaymentMethods extends AppCompatActivity {

    ImageView back, card, cash;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_methods);

        back = findViewById(R.id.barsid);
        card = findViewById(R.id.cardmethod);
        cash = findViewById(R.id.cashmethod);

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
                startActivity(inn);
            }
        });

        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inn = new Intent(getApplicationContext(), PayingbyCash.class);
                startActivity(inn);
            }
        });
    }
}