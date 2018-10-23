package ie.wit.aubane.wanderlust10.dialogs;

import android.app.DialogFragment;

public interface DialogListener {
    public void onDialogPositiveClick(DialogFragment dialog);
    public void onDialogNegativeClick(DialogFragment dialog);

}
