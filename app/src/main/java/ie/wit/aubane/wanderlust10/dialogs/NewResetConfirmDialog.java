package ie.wit.aubane.wanderlust10.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

public class NewResetConfirmDialog extends DialogFragment {

    DialogListener listener;

    private String message ="";
    AlertDialog.Builder builder;
    public String origin;
    private String title="Reset";
    private String posiviteMessageText = "Reset";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(title)
                .setMessage(message+"\nAre you sure?")
                .setPositiveButton(posiviteMessageText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogPositiveClick(NewResetConfirmDialog.this);

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.onDialogNegativeClick(NewResetConfirmDialog.this);
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

    public void setOrigin(String origin){
        this.origin = origin;
        switch(origin){
            case "Trips":
                message= "This will erase all trips and entries.";
                break;
            case "Entries":
                message= "This will erase all entries from this trip.";
                break;
            case "SingleTrip":
                message= "The selected trip will be deleted.";
                title="Delete";
                posiviteMessageText="Delete";
                break;
            case "SingleEntry":
                message="The selected entry will be deleted.";
                title="Delete";
                posiviteMessageText="Delete";
                break;
        }
    }



}
