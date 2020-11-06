package Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.semester_project.smd_project.Areas;
import com.semester_project.smd_project.Guider_info;
import com.semester_project.smd_project.R;

import MyRecycleView.MyRvAdapter;
import MyRecycleView.TripHistory;
import MyRecycleView.TripHistoryRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class TripHistoryFragment extends Fragment
{
    RecyclerView rv;
    List<TripHistory> usertriphistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.triphistory_fragment, container, false);
        getActivity().setTitle("Profile");

        usertriphistory = new ArrayList<>();

        usertriphistory.add(new TripHistory("Jahan", "21/02/2020", "$500"));
        usertriphistory.add(new TripHistory("Raqeeb", "03/02/2020", "$400"));
        usertriphistory.add(new TripHistory("Hasnain", "27/02/2020", "$600"));
        usertriphistory.add(new TripHistory("Akram", "21/03/2020", "$900"));
        usertriphistory.add(new TripHistory("Khalid", "21/02/2020", "$500"));
        usertriphistory.add(new TripHistory("Aleem", "21/09/2020", "$200"));
        usertriphistory.add(new TripHistory("Sobo", "09/02/2020", "$100"));
        usertriphistory.add(new TripHistory("Faheem", "21/02/2020", "$500"));

        rv = root.findViewById(R.id.rvtriphistory);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        lm.setStackFromEnd(true);
        rv.setLayoutManager(lm);
        TripHistoryRVAdapter adapter = new TripHistoryRVAdapter(usertriphistory, getContext());
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //rv.smoothScrollToPosition(rv.getAdapter().getItemCount());
        return root;
    }
}
