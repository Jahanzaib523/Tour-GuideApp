package MyRecycleView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.semester_project.smd_project.Activities.PaymentMethods;
import com.semester_project.smd_project.Guider_info;
import com.semester_project.smd_project.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Models.TripInfo;

public class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyViewHolder> implements Serializable
{
    List<Guider_info> ls;
    Context c;
    TripInfo tripInfo;
    Context context;
    String userid;

    public MyRvAdapter(List<Guider_info> ls, Context c, TripInfo tripInfo_, String UID) {
        this.c=c;
        this.ls=ls;
        tripInfo = tripInfo_;
        userid = UID;
    }

    @NonNull
    @Override
    public MyRvAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemrow= LayoutInflater.from(c).inflate(R.layout.box,parent,false);
        return new  MyViewHolder(itemrow);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRvAdapter.MyViewHolder holder, final int position) {
        holder.name.setText(ls.get(position).getName());
        holder.age.setText(ls.get(position).getAge() + " Years");
        holder.experience.setText("Working for " + ls.get(position).getExperience() + " Years");
        holder.price.setText(ls.get(position).getPrice());
        holder.area.setText(ls.get(position).getCountry());
        Picasso.get().load(ls.get(position).getImage()).into(holder.img);
        holder.email_.setText(ls.get(position).getEmail());
        holder.boxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (context, PaymentMethods.class);
                in.putExtra("UID", "karmaexists11@gmail.com");
                in.putExtra("StartLocation", tripInfo.getFromlocation());
                in.putExtra("EndLocation", tripInfo.getTolocation());
                in.putExtra("StartDate", tripInfo.getStartdate());
                in.putExtra("EndDate", tripInfo.getEnddate());
                in.putExtra("GuiderID", ls.get(position).getEmail());
                if(userid == "karmaexists11@gmail.com") { Log.d("msg", "True");}
                context.startActivity(in);
            }
        });
        /*holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent (context, PaymentMethods.class);
                in.putExtra("UID", tripInfo.getUID());
                in.putExtra("StartLocation", tripInfo.getFromlocation());
                in.putExtra("EndLocation", tripInfo.getTolocation());
                in.putExtra("StartDate", tripInfo.getStartdate());
                in.putExtra("EndDate", tripInfo.getEnddate());
                in.putExtra("GuiderID", ls.get(position).getEmail().toString());
                context.startActivity(in);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, age, experience, price, area, email_;
        ImageView img;
        //ProgressBar progressBar;

        Button btn;
        LinearLayout boxx;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            age=itemView.findViewById(R.id.age);
            img = itemView.findViewById(R.id.guiderimage);
            experience=itemView.findViewById(R.id.experience);
            price=itemView.findViewById(R.id.budget);
            area = itemView.findViewById(R.id.area);
            boxx=itemView.findViewById(R.id.box);
            btn = itemView.findViewById(R.id.proceedtopay);
            email_ =itemView.findViewById(R.id.email);
            context = itemView.getContext();
        }
    }

    public void filteredlst(ArrayList<Guider_info> filter_ls)
    {
        ls = filter_ls;
        notifyDataSetChanged();
    }
}
