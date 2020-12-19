package Fragments;

import androidx.fragment.app.Fragment;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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

import com.google.firebase.auth.FirebaseAuth;
import com.semester_project.smd_project.Activities.Add_Cataloges;
import com.semester_project.smd_project.R;
import java.util.Calendar;

public class CreateTrip_Fragment extends Fragment
{
    TextView startdate;
    EditText enddate;
    Button tourguides;
    DatePickerDialog.OnDateSetListener setListener;
    DatePickerDialog.OnDateSetListener setListener1;
    String getdate;
    String flag = "End";
    String USER;
    AutoCompleteTextView from, to;
    String [] strs = {"Islamabad", "Karachi", "Lahore", "Gujranwala", "Mianwali", "Daska", "Quetta", "Hyderabad", "Gilgit", "Muzzaffarabad"};
    ArrayAdapter<String> adpstr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_create_trip, container, false);
        tourguides = root.findViewById(R.id.gotocataloge);
        startdate = root.findViewById(R.id.startdatetext);
        enddate = root.findViewById(R.id.enddatetext);
        from = root.findViewById(R.id.fromm);
        to = root.findViewById(R.id.too);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DATE);

        USER = FirebaseAuth.getInstance().getCurrentUser().getEmail();

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
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext()/*, R.style.Theme_AppCompat_Dialog_MinWidth*/, setListener, year, month, day);
                //datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = dayOfMonth + "/"+month+"/"+year;
                startdate.setText(date);
            }
        };

        enddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext()/*, R.style.Theme_AppCompat_Dialog_MinWidth*/, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month + 1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        enddate.setText(date);
                    }
                }, year, month, day);
                //datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        tourguides.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!from.getText().toString().isEmpty() && !to.getText().toString().isEmpty() && !startdate.getText().toString().isEmpty()
                && !enddate.getText().toString().isEmpty())
                {
                    Intent myIntent = new Intent(getContext(), Add_Cataloges.class);
                    myIntent.putExtra("Useremail_ID", USER);
                    myIntent.putExtra("startlocation", from.getText().toString());
                    myIntent.putExtra("endlocation", to.getText().toString());
                    myIntent.putExtra("startdate", startdate.getText().toString());
                    myIntent.putExtra("enddate", enddate.getText().toString());
                    startActivity(myIntent);
                }
                else
                {
                    Toast.makeText(getContext(), "Fill all the fields!", Toast.LENGTH_LONG).show();
                }
            }
        });
        return root;
    }
}