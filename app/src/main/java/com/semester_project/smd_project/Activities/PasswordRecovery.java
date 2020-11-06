package com.semester_project.smd_project.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.semester_project.smd_project.R;

public class PasswordRecovery extends AppCompatActivity {
    private Button recoverpassclick, backtomenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        recoverpassclick = findViewById(R.id.submitemail);
        backtomenu = findViewById(R.id.backtosignup);

        recoverpassclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent passrecoverintent = new Intent(PasswordRecovery.this, SignIn.class);
                startActivity(passrecoverintent);
                finish();
            }
        });

        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtomenuintent = new Intent(PasswordRecovery.this, SignUp.class);
                startActivity(backtomenuintent);
                finish();
            }
        });

    }
}