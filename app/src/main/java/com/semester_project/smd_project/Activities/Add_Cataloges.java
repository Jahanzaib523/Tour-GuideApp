package com.semester_project.smd_project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.semester_project.smd_project.R;

import Fragments.CreateTrip_Fragment;

public class Add_Cataloges extends AppCompatActivity {

    ImageView bck;
    Button proceedtotourguides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__cataloges);

        bck = findViewById(R.id.barsid);
        proceedtotourguides = findViewById(R.id.proceedtopay);

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), CreateTrip_Fragment.class);
                startActivity(i);
            }
        });

        proceedtotourguides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (getApplicationContext(), TourGuideSelection.class);
                startActivity(i);
            }
        });
    }
}