package MyRecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.semester_project.smd_project.Guider_info;
import com.semester_project.smd_project.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import Models.Orders;

public class MyOrdersRecycleView extends RecyclerView.Adapter<MyOrdersRecycleView.MyViewHolder>
{
    List<Orders> ls;
    Context c;
    Context context;

    public MyOrdersRecycleView (List<Orders> ls, Context c)
    {
        this.c=c;
        this.ls=ls;
    }

    @NonNull
    @Override
    public MyOrdersRecycleView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(c).inflate(R.layout.order_box,parent,false);
        return new  MyViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyOrdersRecycleView.MyViewHolder holder, final int position)
    {
        holder.name_.setText(ls.get(position).getGuider_name());
        holder.from.setText(ls.get(position).getFrom_location());
        holder.to.setText(ls.get(position).getTo_location());
        holder.email_.setText(ls.get(position).getGuider_email());
        holder.startloc.setText(ls.get(position).getStar_tdate());
        holder.endloc.setText(ls.get(position).getEnd_date());
        holder.order_status.setText(ls.get(position).getOrder_status());
        holder.pay_.setText(ls.get(position).getPayment());
        Picasso.get().load(ls.get(position).getImage()).into(holder.img);
        holder.boxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,position + "",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name_, from, to, startloc, endloc, email_, pay_, order_status;
        ImageView img;

        LinearLayout boxx;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name_=itemView.findViewById(R.id.nameofguider_);
            img = itemView.findViewById(R.id.orderimage);
            from = itemView.findViewById(R.id.fromlocation);
            to = itemView.findViewById(R.id.tolocation);
            startloc = itemView.findViewById(R.id.startdate_);
            endloc = itemView.findViewById(R.id.enddate_);
            boxx=itemView.findViewById(R.id.orderbox);
            email_ =itemView.findViewById(R.id.emailofguider);
            pay_ = itemView.findViewById(R.id.payment);
            order_status = itemView.findViewById(R.id.inprogress);
            context = itemView.getContext();
        }
    }

    public void filteredlst(ArrayList<Orders> filter_ls)
    {
        ls = filter_ls;
        notifyDataSetChanged();
    }
}

