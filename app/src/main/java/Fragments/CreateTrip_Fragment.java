package Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.semester_project.smd_project.Activities.Add_Cataloges;
import com.semester_project.smd_project.Activities.TourGuideSelection;
import com.semester_project.smd_project.R;

import java.text.DateFormat;
import java.util.Calendar;

import Pickers.DatePickerFragment;

public class CreateTrip_Fragment extends Fragment implements DatePickerDialog.OnDateSetListener
{
    EditText startdate;
    EditText enddate;
    Button tourguides;
    String getdate;
    String flag = "End";

    AutoCompleteTextView from, to;
    String [] strs = {"Islamabad", "Karachi", "Lahore", "Gujranwala", "Mianwali", "Daska", "Quetta", "Hyderabad", "Gilgit", "Muzzaffarabad"};
    ArrayAdapter<String> adpstr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_create_trip, container, false);
        getActivity().setTitle("Create a trip");

        tourguides = root.findViewById(R.id.gotocataloge);
        startdate = root.findViewById(R.id.startdatetext);
        enddate = root.findViewById(R.id.enddatetext);
        from = root.findViewById(R.id.fromm);
        to = root.findViewById(R.id.too);

        adpstr = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line, strs);
        from.setThreshold(1);
        from.setAdapter(adpstr);

        from.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                from.setText(adpstr.getItem(position));
            }
        });

        to.setThreshold(1);
        to.setAdapter(adpstr);

        to.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                to.setText(adpstr.getItem(position));
            }
        });

        adpstr.notifyDataSetChanged();

        startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DialogFragment datepickerstart = new DatePickerFragment();
                datepickerstart.show(getActivity().getSupportFragmentManager(), "Date Picker");
                String s = getdate;
                startdate.setText(s);
            }
        });

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                flag = "Start";
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "DatePicker");
                //enddate.setText(getdate);
            }
        });

        tourguides.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(getContext(), Add_Cataloges.class);
                startActivity(myIntent);
            }
        });
        return root;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        populateSetDate(year, month + 1, dayOfMonth);
    }

    public void populateSetDate(int year, int month, int day) {

        if(flag.equals("From")) {
            startdate.setText(day + "/" + month + "/" + year);
        }
        else {
            enddate.setText(day + "/" + month + "/" + year);
        }
    }
}