package com.pooja.myproject.Dialogboxes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.pooja.myproject.DatabaseActivity.DBHelper;

/**
 * Created by Pooja on 1/23/2017.
 */

public class DeleteBox extends DialogFragment
{
    DBHelper helper;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        helper=new DBHelper(getActivity());

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete");
        builder.setMessage("Do you want to delete this item?");
        final int position=getArguments().getInt("Position");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int id=getArguments().getInt("Id");
                Toast.makeText(getActivity(),"Id:"+id,Toast.LENGTH_LONG).show();
                long delete=helper.deleteitemfromfinishedlist(id);
                if(delete>0)
                {
                    Toast.makeText(getActivity(),"Deleted",Toast.LENGTH_LONG).show();
                    dismiss();

                }else
                {
                    Toast.makeText(getActivity(),"Not deleted>>>Something went wrong",Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        Dialog dialog=builder.create();
        return dialog;
    }

}
