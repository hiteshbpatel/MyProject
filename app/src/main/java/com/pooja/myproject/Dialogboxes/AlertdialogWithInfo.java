package com.pooja.myproject.Dialogboxes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pooja.myproject.CompletionAndNotification;
import com.pooja.myproject.DatabaseActivity.DBHelper;
import com.pooja.myproject.R;

/**
 * Created by Pooja on 1/22/2017.
 */

public class AlertdialogWithInfo extends DialogFragment
{
    EditText et_title,et_desc;
    DBHelper dbHelper;
    TextView tv_date;
    Button changedate_btn,update_btn,cancel_btn;
    DatePicker datePicker;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflator=getActivity().getLayoutInflater();
        View view=inflator.inflate(R.layout.alertdialog_withinfo,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        init(view);
        setCancelable(false);
        return dialog;
    }
    public void init(View view)
    {
        et_title= (EditText) view.findViewById(R.id.editText_title);
        et_desc= (EditText) view.findViewById(R.id.editText_description);
        tv_date= (TextView) view.findViewById(R.id.textView_date);
        datePicker=(DatePicker)view.findViewById(R.id.datePicker_alert);
        dbHelper=new DBHelper(getActivity());
        //getting bundle arguments from main activity
        final String t=getArguments().getString("Title");
        final String desc=getArguments().getString("Description");
        final String date=getArguments().getString("Date");
       // final int position=getArguments().getInt("Id");

        //setting the bundle values in the dialog

        et_title.setText(t);
        et_desc.setText(desc);
        tv_date.setText(date);

        changedate_btn= (Button) view.findViewById(R.id.button_setdate);
        changedate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int day=datePicker.getDayOfMonth();
                int month=datePicker.getMonth()+1;
                int year=datePicker.getYear();
                if(day<10 && month<10)
                {
                    String date = "0"+day + "/0" + month + "/" + year;
                    tv_date.setText(date);
                }
                else if(day>=10 && month<10)
                {
                    String date =day + "/0" + month + "/" + year;
                    tv_date.setText(date);
                }
                else if(day<10 && month>=10 )
                {
                    String date = "0"+day + "/" + month + "/" + year;
                    tv_date.setText(date);
                }
                else{
                    String date = day + "/" + month + "/" + year;
                    tv_date.setText(date);
                }

            }
        });
        update_btn= (Button) view.findViewById(R.id.button_update);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //int id=position+1;
                final int id=getArguments().getInt("Id");
                Toast.makeText(getActivity(),"id"+id,Toast.LENGTH_LONG).show();
                //changed values to be updated
                final String U_title=et_title.getText().toString();
                final String U_desc=et_desc.getText().toString();
                final String U_date=tv_date.getText().toString();

                long update=dbHelper.updaterow(id,U_title,U_desc,U_date);
                if(update>0)
                {
                    Toast.makeText(getActivity(),"Successfully updated",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getActivity(),CompletionAndNotification.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_LONG).show();
                }
                dismiss();
            }
        });
        cancel_btn=(Button)view.findViewById(R.id.button_cancel);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
