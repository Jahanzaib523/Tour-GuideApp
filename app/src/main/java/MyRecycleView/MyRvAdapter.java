package MyRecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.semester_project.smd_project.Guider_info;
import com.semester_project.smd_project.R;

import java.util.ArrayList;
import java.util.List;


public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder>
{
    List<Guider_info> ls;
    Context c;
    public MyRvAdapter(List<Guider_info> ls, Context c) {
        this.c=c;
        this.ls=ls;
    }

    @NonNull
    @Override
    public MyRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(c).inflate(R.layout.box,parent,false);
        return new  MyViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRvAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(ls.get(position).get_Name());
        holder.age.setText(ls.get(position).get_Age());
        holder.experience.setText(ls.get(position).get_Name());
        holder.price.setText(ls.get(position).get_Age());
        holder.boxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,position+"",Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, areas, experience, price;
        LinearLayout boxx;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            age=itemView.findViewById(R.id.age);
            //areas=itemView.findViewById(R.id.email);
            experience=itemView.findViewById(R.id.experience);
            price=itemView.findViewById(R.id.price);
            boxx=itemView.findViewById(R.id.box);
        }
    }

    public void filteredlst(ArrayList<Guider_info> filter_ls)
    {
        ls = filter_ls;
        notifyDataSetChanged();
    }
}
