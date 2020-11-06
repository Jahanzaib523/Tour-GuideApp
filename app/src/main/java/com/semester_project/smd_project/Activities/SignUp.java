package com.semester_project.smd_project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.semester_project.smd_project.R;

public class SignUp extends AppCompatActivity {
    private Button signupclick;
    private TextView gotosigninpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signupclick = findViewById(R.id.singnupbtn);
        gotosigninpage = findViewById(R.id.gotosign);
        signupclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupbtnclicked = new Intent(SignUp.this, UserVerification.class);
                startActivity(signupbtnclicked);
            }
        });

        gotosigninpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosigninclickedintent = new Intent(SignUp.this, SignIn.class);
                startActivity(gotosigninclickedintent);
                finish();
            }
        });
    }
}