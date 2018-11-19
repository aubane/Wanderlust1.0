package ie.wit.aubane.wanderlust10.activities;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.List;

import ie.wit.aubane.wanderlust10.R;
import ie.wit.aubane.wanderlust10.dialogs.DialogListener;
import ie.wit.aubane.wanderlust10.dialogs.EditDialog;
import ie.wit.aubane.wanderlust10.dialogs.NewResetConfirmDialog;
import ie.wit.aubane.wanderlust10.models.Entry;
import ie.wit.aubane.wanderlust10.models.EntryAdapter;

public class TripView extends BaseClass implements DialogListener {

    ListView listEntry;
    EntryAdapter adapter;
    List<Entry> entries_list;
    int trip_id;
    int single_entry_id=-1;

    TextView title_trip, destination, start_date, end_date;
    FloatingActionButton new_entry_button;
    int start_year, start_month, start_day, end_year, end_month, end_day;

    DatePickerDialog.OnDateSetListener listener_start, listener_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripview);
        Log.v("Wanderlust", "Arrived in TripView Activity");

        listener_start = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                start_date.setText(year + "-" + month + "-" + dayOfMonth);
                app.dbManager.getAll().get(trip_id).start = start_date.getText().toString();
            }
        };

        listener_end = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                end_date.setText(year + "-" + month + "-" + dayOfMonth);
                app.dbManager.getAll().get(trip_id).end = end_date.getText().toString();
            }
        };

        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        trip_id = Integer.parseInt(extras.getString("tripID"));
        Log.v("Wanderlust", "TripView: id passed from Intent: "+trip_id);

        listEntry = findViewById(R.id.list_entries);
        entries_list = app.dbManager.getEntriesFromTrip(trip_id);
        adapter = new EntryAdapter(this, entries_list);
        listEntry.setAdapter(adapter);
        listEntry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EntryView.class);
                Bundle extras = new Bundle();
                extras.putString("tripID", ""+trip_id);
                extras.putString("entryID", ""+position);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        new_entry_button = findViewById(R.id.new_entry_button);

        title_trip = findViewById(R.id.title_trip);
        destination = findViewById(R.id.destination);
        start_date = findViewById(R.id.start_date);
        end_date = findViewById(R.id.end_date);

        title_trip.setText(app.dbManager.getAll().get(trip_id).name);
        destination.setText(app.dbManager.getAll().get(trip_id).destination);
        start_date.setText(app.dbManager.getAll().get(trip_id).start);
        end_date.setText(app.dbManager.getAll().get(trip_id).end);

        splitDate("start", app.dbManager.getAll().get(trip_id).start);
        splitDate("end", app.dbManager.getAll().get(trip_id).end);

        destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((EditText)findViewById(R.id.textField)).setText(destination.getText());
                EditDialog dialog = new EditDialog();
                dialog.setOrigin("destination");
                dialog.show(getFragmentManager(), "TEST");
            }
        });

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TripView.this, listener_start, start_year, start_month, start_day);
                datePickerDialog.show();
            }
        });

        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        TripView.this, listener_end, end_year, end_month, end_day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.v("Wanderlust", "TripView: onResume()");
        //entries_list.clear();
        //entries_list.addAll(app.dbManager.getEntriesFromTrip(trip_id));
        adapter.notifyDataSetChanged();
    }

    public void createNewEntry(View view){
        Intent intent = new Intent(getApplicationContext(), NewEntry.class);
        intent.putExtra("tripID", ""+trip_id);
        startActivity(intent);
    }

    public void editDetails(View view){

    }

    @Override
    public void menu_reset(MenuItem item){
        showNewDialog("Entries");
    }

    public void showNewDialog(String origin) {
        // Create an instance of the dialog fragment and show it
        NewResetConfirmDialog dialog = new NewResetConfirmDialog();
        dialog.setOrigin(origin);
        dialog.show(getFragmentManager(), "TEST");
    }

    private void reset(){
        app.dbManager.deleteEntriesFromTrip(trip_id);
        entries_list.clear();
        entries_list.addAll(app.dbManager.getEntriesFromTrip(trip_id));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if(dialog instanceof EditDialog){
            Log.v("Wanderlust", "editdialog approved - TripView class");
            String text = ((EditDialog) dialog).editText.getText().toString();
            switch(((EditDialog) dialog).origin){
                case "destination":
                    destination.setText(text);
                    app.dbManager.getAll().get(trip_id).destination = text;
                    break;
            }
        }else if(dialog instanceof NewResetConfirmDialog){
            switch(((NewResetConfirmDialog)dialog).origin){
                case "Entries":
                    reset();
                    break;
                case "SingleEntry":
                    Log.v("Wanderlust", "should delete entry");
                    app.dbManager.deleteSingleEntry(single_entry_id);
                    entries_list.clear();
                    entries_list.addAll(app.dbManager.getEntriesFromTrip(trip_id));
                    single_entry_id = -1;
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Log.v("Wanderlust", "reset canceled - TripView class");
    }

    private void splitDate(String whichOne, String date){
        switch(whichOne){
            case "start":
                start_year = Integer.parseInt(date.split("-")[0]);
                start_month = Integer.parseInt(date.split("-")[1]);
                start_day = Integer.parseInt(date.split("-")[2]);
                break;
            case "end":
                end_year = Integer.parseInt(date.split("-")[0]);
                end_month = Integer.parseInt(date.split("-")[1]);
                end_day = Integer.parseInt(date.split("-")[2]);
        }
    }

    public void showMenu (View view)
    {
        single_entry_id = ((ListView)view.getParent().getParent()).getPositionForView(view);
        Log.v("Wanderlust", ""+single_entry_id);
        PopupMenu menu = new PopupMenu (this, view);
        menu.inflate (R.menu.options_menu_layout);
        menu.show();
    }

    public void option_delete(MenuItem item){
        Log.i ("Wanderlust", "option_delete");
        showNewDialog("SingleEntry");
    }
    public void option_edit(MenuItem item){
        Log.i ("Wanderlust", "option_edit");
    }
}
