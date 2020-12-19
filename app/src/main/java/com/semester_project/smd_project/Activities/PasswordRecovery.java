package com.semester_project.smd_project.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.semester_project.smd_project.R;

public class PasswordRecovery extends AppCompatActivity {
    private Button recoverpassclick, backtomenu;
    TextView recoveryfailed;
    private EditText recoveryemail;
    private FirebaseDatabase mDatabase;
    private DatabaseReference dbReference;
    private ProgressBar progress;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        recoverpassclick = findViewById(R.id.submitemail);
        backtomenu = findViewById(R.id.backtosignup);
        recoveryfailed = findViewById(R.id.recoveryfailedmsg);
        recoveryemail = findViewById(R.id.recoveryemail);
        String emailID = recoveryemail.getText().toString();
        progress = findViewById(R.id.progressbar6);
        mDatabase = FirebaseDatabase.getInstance();
        dbReference = mDatabase.getReference("USER");
        auth = FirebaseAuth.getInstance();
        RecoverPasswordClick();
        BackToLogin();
    }

    public void RecoverPasswordClick()
    {
        recoverpassclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recoveryfailed.setVisibility(View.GONE);
                progress.setVisibility(View.VISIBLE);
                RecoverPasswordNow();
            }
        });
    }

    public void BackToLogin()
    {
        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backtomenuintent = new Intent(PasswordRecovery.this, SignUp.class);
                startActivity(backtomenuintent);
                finish();
            }
        });
    }

    public void RecoverPasswordNow()
    {
        auth.sendPasswordResetEmail(recoveryemail.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            progress.setVisibility(View.GONE);
                            recoveryfailed.setVisibility(View.GONE);
                            Intent passrecoverintent = new Intent(PasswordRecovery.this, ForgotPasswordRecovery.class);
                            startActivity(passrecoverintent);
                            finish();
                        }
                        else
                        {
                            recoveryfailed.setVisibility(View.VISIBLE);
                        }
                    }
                });
        /*final String[] password = {""};
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d: snapshot.getChildren())
                {
                    if(d.child("useremail").getValue().equals(recoveryemail.getText().toString()))
                    {
                        password[0] = d.child("password").getValue(String.class);
                        String pass = password[0];
                        SendPasswordViaEmail(recoveryemail.getText().toString(), pass);
                        progress.setVisibility(View.GONE);
                        recoverpassclick.setVisibility(View.GONE);

                    }

                }
                recoveryfailed.setVisibility(View.VISIBLE);
                progress.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progress.setVisibility(View.GONE);
            }
        });*/
    }
}