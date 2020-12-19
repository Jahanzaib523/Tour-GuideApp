package com.semester_project.smd_project.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.semester_project.smd_project.R;

import Models.User;
import Models.Wallet;
import de.hdodenhof.circleimageview.CircleImageView;

public class Create_UserProfile extends AppCompatActivity
{
    private FirebaseAuth signupMe;
    private Wallet wallet;
    private ProgressBar progress_bar;
    private static final int PICK_IMG = 1;
    private Uri photo = null;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USER = "USER";
    Button bck;
    String coverimage_;
    CircleImageView profileimage;
    LinearLayout coverImage;
    Bitmap xyz;
    Button submitbtn;
    EditText name, email, phone, address, country;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__user_profile);

        profileimage = (CircleImageView) findViewById(R.id.profilepic);
        coverImage = findViewById(R.id.l1);
        submitbtn = findViewById(R.id.saveprofilebtn);
        name = findViewById(R.id.username);
        email = findViewById(R.id.useremail);
        phone = findViewById(R.id.userphone);
        address = findViewById(R.id.useraddress);
        country = findViewById(R.id.usercountry);
        progress_bar = findViewById(R.id.progressbar2);
        database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference(USER);
        signupMe = FirebaseAuth.getInstance();
        wallet = new Wallet();
        bck = findViewById(R.id.back);

        final String user_email = getIntent().getStringExtra("email");
        final String password1 = getIntent().getStringExtra("password1");
        final String username_ =getIntent().getStringExtra("username");

        name.setText(username_);
        email.setText(user_email);

        Upload_profilePicture();
        Upload_CoverPicture();

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitbtn.setVisibility(View.GONE);
                bck.setVisibility(View.GONE);
                progress_bar.setVisibility(View.VISIBLE);
                String number = phone.getText().toString();
                String address_ = address.getText().toString();
                String country_ = country.getText().toString();
                Upload(username_, user_email, password1, number, address_, country_);
            }
        });

        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (getApplicationContext(), SignUp.class);
                startActivity(i);
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMG && resultCode == RESULT_OK)
        {
            photo  = data.getData();
            try
            {
                ImageDecoder.Source source = ImageDecoder.createSource(this.getContentResolver(), photo);
                xyz = ImageDecoder.decodeBitmap(source);
                profileimage.setImageBitmap(xyz);
            }
            catch(Exception e)
            {
                e.getStackTrace();
            }
        }
    }

    protected void Upload_profilePicture()
    {
        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Picture"), PICK_IMG);
            }
        });
    }

    protected void Upload_CoverPicture()
    {
        coverImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery, "Picture"), PICK_IMG);
            }
        });
    }

    public void RegisterUser(String user_email, String password1)
    {
        signupMe.createUserWithEmailAndPassword(user_email, password1).addOnCompleteListener(Create_UserProfile.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(!task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(), "User already exists with this email", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    HideKeyboard(Create_UserProfile.this);
                }
                else
                {
                    FirebaseUser user = signupMe.getCurrentUser();
                    UpdateUI(user);
                    HideKeyboard(getApplicationContext());
                    progress_bar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void UpdateUI(FirebaseUser User)
    {
        String key = mDatabase.push().getKey();
        mDatabase.child(key).setValue(user);
        Intent signupbtnclicked = new Intent(Create_UserProfile.this, SignIn.class);
        startActivity(signupbtnclicked);
    }

    public void Upload(final String username_, final String user_email, final String password1, final String number, final String address_, final String country_)
    {
        if(photo != null)
        {
            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
            StorageReference storageReference = firebaseStorage.getReference();
            storageReference = storageReference.child("Images/" + phone);
            storageReference.putFile(photo)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
                            task.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri)
                                {
                                    String img = uri.toString();
                                    user = new User(username_, user_email, password1, img, coverimage_, number, address_, country_, "0");
                                    RegisterUser(user_email, password1);
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                             //submitbtn.setEnabled(true);
                                             //bck.setEnabled(true);
                                            submitbtn.setVisibility(View.VISIBLE);
                                            bck.setVisibility(View.VISIBLE);
                                        }
                                    });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            submitbtn.setVisibility(View.VISIBLE);
                            bck.setVisibility(View.VISIBLE);
                        }
                    });
        }
        else {
            Toast.makeText(getApplicationContext(), "Upload All Images", Toast.LENGTH_SHORT).show();
            submitbtn.setVisibility(View.VISIBLE);
            bck.setVisibility(View.VISIBLE);
            progress_bar.setVisibility(View.GONE);
        }
    }

    public void HideKeyboard(Context c)
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(c);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}