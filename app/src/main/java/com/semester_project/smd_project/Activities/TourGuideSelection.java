package com.semester_project.smd_project.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.semester_project.smd_project.Areas;
import com.semester_project.smd_project.Guider_info;
import com.semester_project.smd_project.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Models.TripInfo;
import MyRecycleView.MyRvAdapter;

public class TourGuideSelection extends AppCompatActivity implements Serializable
{
    //Button proceedtopay;
    private ProgressBar progress;
    ImageView gotocatalogue;
    EditText searchguider;
    FirebaseDatabase mDatabase;
    DatabaseReference dbReference;
    RecyclerView rv;
    MyRvAdapter adapter;
    List<Guider_info> guiders;
    String USERID;
    List<Areas> area = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourguideselection);

        guiders = new ArrayList<>();
        //proceedtopay = findViewById(R.id.proceedtopayment);
        gotocatalogue = findViewById(R.id.barsid);
        searchguider = findViewById(R.id.searchguide);
        mDatabase = FirebaseDatabase.getInstance();
        dbReference = mDatabase.getReference("GUIDER");
        USERID = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        progress = findViewById(R.id.progressbar7);

        ReceiveGuiders();

        rv=findViewById(R.id.rv);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        lm.setStackFromEnd(true);
        rv.setLayoutManager(lm);
        adapter = new MyRvAdapter(guiders,this, (TripInfo) getIntent().getSerializableExtra("mytripinfo"), USERID);
        rv.setAdapter(adapter);

        gotocatalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (getApplicationContext(), Add_Cataloges.class);
                finish();
                HideKeyboard();
                startActivity(in);
            }
        });

        searchguider.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                     filter(s.toString());
            }
        });
    }

    public  void filter(String txt)
    {
         ArrayList<Guider_info> lst = new ArrayList<>();
         for(Guider_info item : guiders)
         {
             if(item.getName().toLowerCase().contains(txt.toLowerCase()))
             {
                 lst.add(item);
             }
             else if (item.getExperience().toLowerCase().contains(txt.toLowerCase()))
             {
                 lst.add(item);
             }
             else if (item.getPrice().toLowerCase().contains(txt.toLowerCase()))
             {
                 lst.add(item);
             }
         }

         adapter.filteredlst(lst);
    }

    public void ReceiveGuiders()
    {
        progress.setVisibility(View.VISIBLE);
       dbReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot)
           {
               for (DataSnapshot d: snapshot.getChildren())
               {
                   String name = d.child("username").getValue(String.class);
                   String age = d.child("age").getValue(String.class);
                   String profilepic = d.child("profilepic").getValue(String.class);
                   String experience = d.child("experience").getValue(String.class);
                   String budget = d.child("budget").getValue(String.class);
                   String useremail = d.child("useremail").getValue(String.class);
                   String phone = d.child("phone").getValue(String.class);
                   String address = d.child("address").getValue(String.class);
                   String country = d.child("country").getValue(String.class);

                   guiders.add(new Guider_info(name, age, profilepic, experience, budget, useremail, phone, address, country));
                   adapter.notifyDataSetChanged();
                   progress.setVisibility(View.GONE);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {
               progress.setVisibility(View.GONE);
           }
       });
       progress.setVisibility(View.GONE);
    }

    protected void HideKeyboard()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(getApplicationContext());
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}