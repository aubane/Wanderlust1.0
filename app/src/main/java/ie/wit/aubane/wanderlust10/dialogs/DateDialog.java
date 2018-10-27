package ie.wit.aubane.wanderlust10.dialogs;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class DateDialog extends DatePickerDialog {
    DialogListener listener;

    public DateDialog(@NonNull Context context, @Nullable OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, listener, year, monthOfYear, dayOfMonth);
    }


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        return null;
    }

}
