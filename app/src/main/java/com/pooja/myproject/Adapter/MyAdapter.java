package com.pooja.myproject.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pooja.myproject.Model.Eachrow;
import com.pooja.myproject.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pooja on 1/21/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    Context context;
    ArrayList<Eachrow> arrayeach;
    int[] image={R.drawable.ic_action_good,R.drawable.ic_action_bad,R.drawable.ic_action_event};
    private ClickInterface clickInterface;

    public interface ClickInterface
    {
        public void onsingleclick(int position);
        public void onLongclick(int position);
    }
    public void movetofinished(int position)
    {
        arrayeach.remove(position);
        notifyItemRemoved(position);
    }

    public void setClickInterface(final ClickInterface clickInterface)
    {
        this.clickInterface=clickInterface;
    }

    public MyAdapter(Context context, ArrayList<Eachrow> arrayeach) {
        this.context = context;
        this.arrayeach = arrayeach;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        return new MyViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Eachrow eachrow=arrayeach.get(position);
        holder.date_main_heading.setText(eachrow.getDate());
        holder.title.setText(eachrow.getTitle());
        holder.description.setText(eachrow.getDescription());
        holder.date.setText(eachrow.getDate());
        holder.date_main_heading.setBackgroundColor(Color.parseColor("#e6ffe6"));
        holder.id_db.setText(String.valueOf(eachrow.getId()));
        holder.imageView.setImageResource(image[1]);
        String current=getcurrentdate();
        if(current.equals(eachrow.getDate()))
        {
            holder.date_main_heading.setBackgroundColor(Color.RED);
            //holder.cardView.setCardBackgroundColor(Color.GREEN);
        }
    }

    public String getcurrentdate()
    {
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        return thisDate;
    }

    @Override
    public int getItemCount() {
        return arrayeach.size();
    }

    public void additem(Eachrow eachrow,int position)
    {
        arrayeach.add(eachrow);
        notifyItemInserted(position);
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {
        Context context;
        TextView date_main_heading;
        TextView title;
        TextView description;
        TextView date;
        TextView id_db;
        TextView importance;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(View itemView,Context ctx)
        {
            super(itemView);
            this.context=ctx;
            date_main_heading= (TextView) itemView.findViewById(R.id.main_date_display);
            title= (TextView) itemView.findViewById(R.id.title_card);
            description=(TextView)itemView.findViewById(R.id.description_card);
            date=(TextView)itemView.findViewById(R.id.date_card);
            id_db= (TextView)itemView.findViewById(R.id.card_id_db);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            cardView=(CardView)itemView.findViewById(R.id.cardview);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickInterface.onsingleclick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            //int longclicked=getAdapterPosition()+1;
            clickInterface.onLongclick(getAdapterPosition());
            movetofinished(getAdapterPosition());
            //Toast.makeText(context,"Longclicked on"+getAdapterPosition(),Toast.LENGTH_LONG).show();
            return true;
        }
    }

}
