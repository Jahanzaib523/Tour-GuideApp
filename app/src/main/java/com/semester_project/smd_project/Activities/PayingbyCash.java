package com.semester_project.smd_project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.semester_project.smd_project.R;

public class PayingbyCash extends AppCompatActivity
{
    Button pay;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payingby_cash);

        back = findViewById(R.id.barsid);
        pay = findViewById(R.id.paybycashbtn);

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
                Intent in1 = new Intent(getApplicationContext(), PaymentSuccessful.class);
                startActivity(in1);
                finish();
            }
        });
    }
}