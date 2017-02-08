package com.pooja.myproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.pooja.myproject.Adapter.Finished_adapter;
import com.pooja.myproject.DatabaseActivity.DBHelper;
import com.pooja.myproject.Dialogboxes.DeleAllDialog;
import com.pooja.myproject.Model.FinishedEachrow;

import java.util.ArrayList;

public class FinishedActivity extends AppCompatActivity implements Finished_adapter.ClickFinishedListener {
    RecyclerView finishedrecycler;
    Toolbar finished_toolbar;
    ArrayList<FinishedEachrow> finishedlist;
    DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);
        finished_toolbar= (Toolbar) findViewById(R.id.toolbar_finished);
        setSupportActionBar(finished_toolbar);
        finishedrecycler= (RecyclerView) findViewById(R.id.recycler_finished);
        helper=new DBHelper(this);
        finishedrecycler.setLayoutManager(new LinearLayoutManager(this));
        finishedrecycler.setItemAnimator(new DefaultItemAnimator());
        finishedlist=helper.getfinisheditems();
        int size=finishedlist.size();
        if(size==0)
        {
            Toast.makeText(FinishedActivity.this,"No items in completed list",Toast.LENGTH_LONG).show();
        }
        else
        {
            Finished_adapter finished_adapter=new Finished_adapter(FinishedActivity.this,finishedlist);
            finishedrecycler.setAdapter(finished_adapter);
            finished_adapter.setClickFinishedListener(this);
        }
          }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.finished_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        switch(id)
        {
            case R.id.Back:
                Intent intentback=new Intent(FinishedActivity.this,MainActivity.class);
                startActivity(intentback);
                break;
            case R.id.Delete_all:
                DeleAllDialog deleteall=new DeleAllDialog();
                deleteall.show(getFragmentManager(),"Delete All");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onsinglefinishedclick(int position)
    {
       /* int id=finishedlist.get(position).getId();
        DeleteBox deletealert=new DeleteBox();
        Bundle bundle=new Bundle();
        bundle.putInt("Id",id);
        bundle.putInt("Position",position);
        deletealert.setArguments(bundle);
        deletealert.show(getFragmentManager(),"Delete alert");*/
    }

    @Override
    public void onlongfinishedclick(int position) {
        int id=finishedlist.get(position).getId();
        long result=helper.deleteitemfromfinishedlist(id);
        if(result>0)
        {
            Toast.makeText(FinishedActivity.this,"Event was Deleted Successfully",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(FinishedActivity.this," Not Deleted....Something went wrong",Toast.LENGTH_LONG).show();
        }

    }
}
