package com.pooja.myproject;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.pooja.myproject.Adapter.MyAdapter;
import com.pooja.myproject.DatabaseActivity.DBHelper;
import com.pooja.myproject.Dialogboxes.AlertdialogWithInfo;
import com.pooja.myproject.Dialogboxes.InformationDialog;
import com.pooja.myproject.Model.Eachrow;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements MyAdapter.ClickInterface{
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<Eachrow> arrayList;
    DBHelper helper;
    String currentdate;
    private static final int NOTIFY_ID=100;
    private Parcelable recyclerViewState;
    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        helper=new DBHelper(MainActivity.this);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList=helper.getalldetails();
        //sorting the list
        Collections.sort(arrayList);
        adapter=new MyAdapter(MainActivity.this,arrayList);
        recyclerView.setAdapter(adapter);
        adapter.setClickInterface(this);
        currentdate=adapter.getcurrentdate();
        // Save state
        recyclerViewState = recyclerView.getLayoutManager().onSaveInstanceState();
        // Resore state
        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_items,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        switch (id)
        {
            case R.id.Add_menu:
                InformationDialog infodialog=new InformationDialog();
                infodialog.show(getFragmentManager(),"Information");
                break;
            case R.id.completed_menu:
                Intent intent=new Intent(MainActivity.this,FinishedActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onsingleclick(int position) {

        String title=arrayList.get(position).getTitle();
        String description=arrayList.get(position).getDescription();
        String date=arrayList.get(position).getDate();
        int id=arrayList.get(position).getId();
        AlertdialogWithInfo alertdialogWithInfo=new AlertdialogWithInfo();
        Bundle bundle=new Bundle();
        bundle.putString("Title",title);
        bundle.putString("Description",description);
        bundle.putString("Date",date);
        bundle.putInt("Id",id);
        alertdialogWithInfo.setArguments(bundle);
        alertdialogWithInfo.show(getFragmentManager(),"AlertDialogInfo");
    }

    @Override
    public void onLongclick(int position)
    {
        int id=arrayList.get(position).getId();
        long updatestatus=helper.updatestatus(id);
        if(updatestatus>0)
        {
            Toast.makeText(MainActivity.this,"Updated !!!!! Activity Successfully finished",Toast.LENGTH_LONG).show();
        }else
        {
            Toast.makeText(MainActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
        }
    }

  }
