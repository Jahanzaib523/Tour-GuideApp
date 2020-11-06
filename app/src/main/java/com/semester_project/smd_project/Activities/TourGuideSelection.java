package com.semester_project.smd_project.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.semester_project.smd_project.Areas;
import com.semester_project.smd_project.Guider_info;
import com.semester_project.smd_project.R;

import java.util.ArrayList;
import java.util.List;

import MyRecycleView.MyRvAdapter;

public class TourGuideSelection extends AppCompatActivity
{
    Button proceedtopay;
    ImageView gotocatalogue;
    EditText searchguider;

    RecyclerView rv;
    MyRvAdapter adapter;
    List<Guider_info> guiders;
    List<Areas> area = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourguideselection);

        proceedtopay = findViewById(R.id.proceedtopayment);
        gotocatalogue = findViewById(R.id.barsid);
        searchguider = findViewById(R.id.searchguide);

        guiders = new ArrayList<>();

        guiders.add(new Guider_info("Jahan", "21", "12", "23"));
        guiders.add(new Guider_info("Jahan", "21", "12", "23"));
        guiders.add(new Guider_info("Jahan", "21", "12", "23"));
        guiders.add(new Guider_info("Jahan", "21", "12", "23"));
        guiders.add(new Guider_info("Jahan", "21", "12", "23"));
        guiders.add(new Guider_info("Jahan", "21", "12", "23"));

        rv=findViewById(R.id.rv);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        lm.setStackFromEnd(true);
        rv.setLayoutManager(lm);
        adapter = new MyRvAdapter(guiders,this);
        rv.setAdapter(adapter);

        proceedtopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (getApplicationContext(), PaymentMethods.class);
                finish();
                startActivity(in);
            }
        });

        gotocatalogue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (getApplicationContext(), Add_Cataloges.class);
                finish();
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
             if(item.get_Name().toLowerCase().contains(txt.toLowerCase()))
             {
                 lst.add(item);
             }
             else if (item.get_Year().toLowerCase().contains(txt.toLowerCase()))
             {
                 lst.add(item);
             }
             else if (item.get_Price().toLowerCase().contains(txt.toLowerCase()))
             {
                 lst.add(item);
             }
         }

         adapter.filteredlst(lst);
    }
}