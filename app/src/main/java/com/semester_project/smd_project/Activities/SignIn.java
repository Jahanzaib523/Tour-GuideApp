package com.semester_project.smd_project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.semester_project.smd_project.R;

public class SignIn extends AppCompatActivity {
    private Button signinbtnclicked;
    private TextView forgotpassclick, createnewaccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signinbtnclicked = findViewById(R.id.signbtn);
        forgotpassclick = findViewById(R.id.forgotpassword);
        createnewaccount = findViewById(R.id.createaccount);
        signinbtnclicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signbtnclickedintent = new Intent (SignIn.this, MainActivity.class);
                startActivity(signbtnclickedintent);
                finish();
            }
        });

        forgotpassclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotpassclickedintent = new Intent (SignIn.this, PasswordRecovery.class);
                startActivity(forgotpassclickedintent);
                finish();
            }
        });

        createnewaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createaccountclickedintent = new Intent (SignIn.this, SignUp.class);
                startActivity(createaccountclickedintent);
            }
        });
    }
}