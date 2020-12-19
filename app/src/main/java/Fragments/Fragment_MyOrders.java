package Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.semester_project.smd_project.Guider_info;
import com.semester_project.smd_project.R;
import java.util.ArrayList;
import java.util.List;

import Models.Orders;
import MyRecycleView.MyOrdersRecycleView;

public class Fragment_MyOrders extends Fragment
{
    RecyclerView rv;
    List<Orders> orders;
    private String UID;
    private ProgressBar progress;
    private EditText searchOrder;
    private FirebaseDatabase database;
    private FirebaseDatabase db;
    private MyOrdersRecycleView adapter;
    private DatabaseReference dbReference;
    private DatabaseReference dbRefer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_trips_in_progress, container, false);

        orders = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        searchOrder = root.findViewById(R.id.searchorder);
        db = FirebaseDatabase.getInstance();
        progress = root.findViewById(R.id.progressbar10);
        dbReference = database.getReference("ORDERS");
        dbRefer = db.getReference("GUIDER");
        UID = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        ReceiveGuiders();

        rv = root.findViewById(R.id.rvorders);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        lm.setStackFromEnd(true);
        rv.setLayoutManager(lm);
        adapter = new MyOrdersRecycleView(orders, getContext());
        rv.setAdapter(adapter);
        rv.smoothScrollToPosition(rv.getAdapter().getItemCount());
        adapter.notifyDataSetChanged();

        searchOrder.addTextChangedListener(new TextWatcher() {
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

        return  root;
    }

    public  void filter(String txt)
    {
        ArrayList<Orders> lst = new ArrayList<>();
        for(Orders item : orders)
        {
            if(item.getFrom_location().toLowerCase().contains(txt.toLowerCase()))
            {
                lst.add(item);
            }
            else if (item.getGuider_email().toLowerCase().contains(txt.toLowerCase()))
            {
                lst.add(item);
            }
            else if (item.getTo_location().toLowerCase().contains(txt.toLowerCase()))
            {
                lst.add(item);
            }
            else if (item.getEnd_date().toLowerCase().contains(txt.toLowerCase()))
            {
                lst.add(item);
            }
            else if (item.getStar_tdate().toLowerCase().contains(txt.toLowerCase()))
            {
                lst.add(item);
            }
            else if (item.getGuider_email().toLowerCase().contains(txt.toLowerCase()))
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
                    if(d.child("uid").getValue().equals(UID))
                    {
                        if(d.child("tripstatus").getValue().equals("In Progress"))
                        {
                            orders.add(new Orders("xyz",
                                    d.child("uid").getValue(String.class),
                                    d.child("guiderID").getValue(String.class),
                                    d.child("fromlocation").getValue(String.class),
                                    d.child("tolocation").getValue(String.class),
                                    d.child("startdate").getValue(String.class),
                                    d.child("enddate").getValue(String.class),
                                    d.child("tripstatus").getValue(String.class),
                                    d.child("payment_details").getValue(String.class)));
                            adapter.notifyDataSetChanged();
                        }
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
    }
}
