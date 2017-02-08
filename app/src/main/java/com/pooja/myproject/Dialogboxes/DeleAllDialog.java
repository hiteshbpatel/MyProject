package com.pooja.myproject.Dialogboxes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.pooja.myproject.DatabaseActivity.DBHelper;
import com.pooja.myproject.MainActivity;

/**
 * Created by Pooja on 1/24/2017.
 */

public class DeleAllDialog extends DialogFragment
{
    DBHelper helper;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        helper=new DBHelper(getActivity());
        builder.setTitle("Delete All");
        builder.setMessage("Are you sure you want to delete all iems?");
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(),"Cancel",Toast.LENGTH_LONG).show();
                dismiss();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                long result=helper.deleteall();
                if(result>0)
                {
                    Toast.makeText(getActivity(),"Deleted",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_LONG).show();
                }
            }
        });
        Dialog deletedialogall=builder.create();
        return deletedialogall;
    }
}
