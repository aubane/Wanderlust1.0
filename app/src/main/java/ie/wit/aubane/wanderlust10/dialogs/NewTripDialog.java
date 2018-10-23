package ie.wit.aubane.wanderlust10.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

public class NewTripDialog extends DialogFragment {

    DialogListener listener;

    public static String newName;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        newName="";
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final EditText name = new EditText(getContext());
        name.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setTitle("New Trip")
                .setView(name)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newName = name.getText().toString();
                        Log.v("Wanderlust", "newName: "+newName+" length:"+newName.length());
                        listener.onDialogPositiveClick(NewTripDialog.this);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogNegativeClick(NewTripDialog.this);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(" must implement NoticeDialogListener");
        }
    }

    public String getNewName(){
        return newName;
    }



}
