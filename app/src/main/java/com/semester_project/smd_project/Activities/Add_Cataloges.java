package com.semester_project.smd_project.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.semester_project.smd_project.R;

import java.io.Serializable;

import Fragments.CreateTrip_Fragment;
import Models.TripInfo;

public class Add_Cataloges extends AppCompatActivity{

    ImageView bck;
    Button proceedtotourguides;
    private TripInfo tripInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__cataloges);

        bck = findViewById(R.id.barsid);
        final String id = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        proceedtotourguides = findViewById(R.id.proceedtopay);
        tripInfo = new TripInfo(
                getIntent().getStringExtra("Useremail_ID"),
                getIntent().getStringExtra("startlocation"),
                getIntent().getStringExtra("endlocation"),
                getIntent().getStringExtra("startdate"),
                getIntent().getStringExtra("enddate"));

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ip = new Intent(getApplicationContext(), CreateTrip_Fragment.class);
                startActivity(ip);
            }
        });

        proceedtotourguides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (getApplicationContext(), TourGuideSelection.class);
                i.putExtra("mytripinfo", tripInfo);
                i.putExtra("IDUSER", id);
                startActivity(i);
            }
        });
    }
}