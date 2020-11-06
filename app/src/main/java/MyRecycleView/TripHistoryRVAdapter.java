package MyRecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.semester_project.smd_project.R;

import java.util.List;

import Fragments.TripHistoryFragment;

public class TripHistoryRVAdapter extends RecyclerView.Adapter<TripHistoryRVAdapter.MyViewHolder>
{
    List<TripHistory> ls1;
    Context c;
    public TripHistoryRVAdapter(List<TripHistory> ls, Context c)
    {
        this.c=c;
        this.ls1=ls;
    }

    @NonNull
    @Override
    public TripHistoryRVAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemrow= LayoutInflater.from(c).inflate(R.layout.row, null,true);
        return new  MyViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull TripHistoryRVAdapter.MyViewHolder holder, final int position) {
        holder.user_name.setText(ls1.get(position).get_user_name());
        holder.date.setText(ls1.get(position).get_date());
        holder.spent.setText(ls1.get(position).get_dollars_spent());
    }

    @Override
    public int getItemCount() {
        return ls1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView user_name, date, spent;
        LinearLayout boxxx;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.namee);
            date = itemView.findViewById(R.id.datee);
            spent = itemView.findViewById(R.id.dollars);
            boxxx = itemView.findViewById(R.id.row);
        }
    }
}

