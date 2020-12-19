package com.semester_project.smd_project.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.semester_project.smd_project.R;

public class PaymentSuccessful extends AppCompatActivity {

    ImageView switchtodashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);

        switchtodashboard = findViewById(R.id.barsid);

        switchtodashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in1);
                finish();
            }
        });
    }
}