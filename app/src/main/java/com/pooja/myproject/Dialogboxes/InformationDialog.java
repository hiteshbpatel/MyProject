package com.pooja.myproject.Dialogboxes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pooja.myproject.CompletionAndNotification;
import com.pooja.myproject.DatabaseActivity.DBHelper;
import com.pooja.myproject.R;

/**
 * Created by Pooja on 1/21/2017.
 */

public class InformationDialog extends DialogFragment
{
    EditText title,description;
    DatePicker datePicker;
    TextView textView;
    Button btn_save,btn_cancel,setdate;
    DBHelper helper;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflator=getActivity().getLayoutInflater();
        View view= inflator.inflate(R.layout.informationdialog,null);
        builder.setView(view);
        init(view);
        Dialog dialog=builder.create();
        setCancelable(false);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void init(View view)
    {
        helper=new DBHelper(getActivity());

        title=(EditText)view.findViewById(R.id.editText_title);
        description=(EditText)view.findViewById(R.id.editText_description);
        textView=(TextView)view.findViewById(R.id.textview_dateshow);
        datePicker=(DatePicker)view.findViewById(R.id.datePicker);
        setdate=(Button)view.findViewById(R.id.set_date);
        setdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day=datePicker.getDayOfMonth();
                int month=datePicker.getMonth();
                int year=datePicker.getYear();
                int monthnew=month+1;
                if(day<10 && monthnew<10)
                {
                    String date = "0"+day + "/0" + monthnew + "/" + year;
                    textView.setText(date);
                }
                else if(day>=10 && monthnew<10)
                {
                    String date =day + "/0" + monthnew + "/" + year;
                    textView.setText(date);
                }
                 else if(day<10 && monthnew>=10 )
                {
                    String date = "0"+day + "/" + monthnew + "/" + year;
                    textView.setText(date);
                }
                else{
                    String date = day + "/" + monthnew + "/" + year;
                    textView.setText(date);
                }
            }
        });
        btn_save=(Button)view.findViewById(R.id.save_button);
        btn_cancel=(Button)view.findViewById(R.id.cancel_button);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(title.getText().length()>=5)
                {
                    if(description.getText().length()>=5)
                    {
                        if(textView.length()>0)
                        {
                            addvalues();
                           Intent intentcall=new Intent(getActivity(), CompletionAndNotification.class);
                            intentcall.putExtra("Title",title.getText().toString());
                           startActivity(intentcall);
                            dismiss();


                        }else{Toast.makeText(getActivity(),"Set date",Toast.LENGTH_LONG).show();}
                    }else{Toast.makeText(getActivity(),"Minimum 5 characters need to be entered",Toast.LENGTH_LONG).show();}
                }else{Toast.makeText(getActivity(),"Minimum 5 characters need to be entered",Toast.LENGTH_LONG).show();}
            }
        });
    }
    public long addvalues()
    {
        String t=title.getText().toString();
        String desc=description.getText().toString();
        String textdate=textView.getText().toString();
        long result=helper.insertintodb(t,desc,textdate);
        if(result>0)
         {
          Toast.makeText(getActivity(),"Successfully saved",Toast.LENGTH_LONG).show();
          }else
          {
           Toast.makeText(getActivity(),"Something went wrong..Not saved",Toast.LENGTH_LONG).show();
           }
        return result;
    }




}
