package com.pooja.myproject.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pooja.myproject.Model.FinishedEachrow;
import com.pooja.myproject.R;

import java.util.ArrayList;

/**
 * Created by Pooja on 1/23/2017.
 */

public class Finished_adapter extends RecyclerView.Adapter<Finished_adapter.MyViewHolder> {

    Context context;
    ArrayList<FinishedEachrow> finished_arraylist;
    int image[]={R.drawable.ic_action_good};


    private ClickFinishedListener clickFinishedListener;

    public interface ClickFinishedListener
    {
        public void onsinglefinishedclick(int position);
        public void onlongfinishedclick(int position);
    }

    public void setClickFinishedListener(ClickFinishedListener clickFinishedListener1)
    {
        this.clickFinishedListener=clickFinishedListener1;
    }

    public Finished_adapter(Context context,ArrayList<FinishedEachrow> finished_arraylist) {
        this.context=context;
        this.finished_arraylist=finished_arraylist;
    }

    public void delete(int position)
    {
        finished_arraylist.remove(position);
        notifyItemRemoved(position);
    }
    @Override


    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.finished_card_layout,parent,false);
        return new MyViewHolder(view,context,finished_arraylist);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        FinishedEachrow fin_eachrow=finished_arraylist.get(position);
        holder.title.setText(fin_eachrow.getTitle());
        holder.date.setText(fin_eachrow.getDate());
        holder.id_fin.setText(String.valueOf(fin_eachrow.getId()));
        holder.iv.setImageResource(R.drawable.ic_action_good);
    }

    @Override
    public int getItemCount() {
        return finished_arraylist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener
    {
        TextView title,date,id_fin;
        ImageView iv;
        Context ctx;
        ArrayList<FinishedEachrow> finisedEachrows;
        public MyViewHolder(View itemView,Context context,ArrayList<FinishedEachrow> finisedEachrows)
        {
            super(itemView);
            this.ctx=context;
            this.finisedEachrows=finisedEachrows;
            title= (TextView) itemView.findViewById(R.id.textView_title);
            date=(TextView)itemView.findViewById(R.id.textView_date);
            id_fin=(TextView)itemView.findViewById(R.id.textview_id_fin);
            iv=(ImageView)itemView.findViewById(R.id.imageView_good_done);
           // itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickFinishedListener.onsinglefinishedclick(getAdapterPosition());
            //delete(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {

            clickFinishedListener.onlongfinishedclick(getAdapterPosition());
            delete(getAdapterPosition());
           // Toast.makeText(context,"Long pressed",Toast.LENGTH_LONG).show();
            return true;
        }
    }
}
