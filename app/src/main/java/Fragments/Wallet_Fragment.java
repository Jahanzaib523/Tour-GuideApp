package Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.semester_project.smd_project.R;

public class Wallet_Fragment extends Fragment
{
    String UID;
    private TextView balancetextView;
    private FirebaseDatabase mDatabase;
    private DatabaseReference dbReference;
    private ProgressBar progress;
    private Button finaldepositebutton, depositbtn;
    private EditText amountfield;

    public Wallet_Fragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_wallet, container, false);
        balancetextView = root.findViewById(R.id.balance);
        mDatabase = FirebaseDatabase.getInstance();
        progress = root.findViewById(R.id.progressbar8);
        amountfield = root.findViewById(R.id.deposittextview);
        depositbtn = root.findViewById(R.id.depositbutton1);
        finaldepositebutton = root.findViewById(R.id.depositbutton);

        UID = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        progress.setVisibility(View.VISIBLE);
        dbReference = mDatabase.getReference("USER");

        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot d: snapshot.getChildren())
                {
                    if(d.child("useremail").getValue().equals(UID))
                    {
                        balancetextView.setText("Account Balance $" + d.child("currentbalance").getValue(String.class));
                    }

                    progress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progress.setVisibility(View.GONE);
            }
        });
        progress.setVisibility(View.GONE);

        depositbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                depositbtn.setVisibility(View.GONE);
                amountfield.setVisibility(View.VISIBLE);
                finaldepositebutton.setVisibility(View.VISIBLE);
            }
        });

        finaldepositebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot d: snapshot.getChildren())
                        {
                            if(d.child("useremail").getValue().equals(UID))
                            {
                                String str1 = d.child("currentbalance").getValue(String.class);
                                String str2 = amountfield.getText().toString();
                                String result;
                                try
                                {
                                    int value = Integer.valueOf(str1) + Integer.valueOf(str2);
                                    result = ""+value;

                                    d.getRef().child("currentbalance").setValue(result);
                                    finaldepositebutton.setVisibility(View.GONE);
                                    amountfield.setVisibility(View.GONE);
                                    amountfield.setText("");
                                    depositbtn.setVisibility(View.VISIBLE);
                                }
                                catch(NumberFormatException ex)
                                {
                                    result = "Invalid input";
                                }
                            }
                        }

                        Toast.makeText(getContext(), "Amount Deposited", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        finaldepositebutton.setVisibility(View.GONE);
                        amountfield.setVisibility(View.GONE);
                        depositbtn.setVisibility(View.VISIBLE);
                    }
                });
            }
        });

        finaldepositebutton.setVisibility(View.GONE);
        amountfield.setVisibility(View.GONE);
        depositbtn.setVisibility(View.VISIBLE);
        return root;
    }
}
