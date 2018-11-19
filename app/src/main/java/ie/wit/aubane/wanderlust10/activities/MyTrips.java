package ie.wit.aubane.wanderlust10.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.text.method.SingleLineTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ie.wit.aubane.wanderlust10.dialogs.DialogListener;
import ie.wit.aubane.wanderlust10.R;
import ie.wit.aubane.wanderlust10.dialogs.NewResetConfirmDialog;
import ie.wit.aubane.wanderlust10.models.Trip;
import ie.wit.aubane.wanderlust10.models.TripAdapter;

public class MyTrips extends BaseClass implements DialogListener{

    //DialogListener listener;
    public List<Trip> trips;
    public ListView listTrips;
    public TripAdapter adapter;
    public String[] sortOptions = {"name", "date", "location"};
    public Spinner dropDownOptions;
    ImageView more;
    int single_trip_id=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytrips);
        Log.v("Wanderlust", "Arrived in MyTrips Activity");
        dropDownOptions = findViewById(R.id.sort_option);
        trips = app.dbManager.getAll();
        Log.v("Wanderlust", ""+trips.size());

        //set up the ListView for the trip list
        listTrips = findViewById(R.id.list_trips);
        adapter = new TripAdapter(this, trips);
        listTrips.setAdapter(adapter);
        //adds an onItemClickListener, forwards to activity TripView and sets the title correctly
        listTrips.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TripView.class);
                Bundle extras = new Bundle();
                extras.putString("tripID", ""+position);
                intent.putExtras(extras);
                startActivity(intent);

            }
        });

        Log.v("Wanderlust", ""+listTrips.getItemAtPosition(0));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.new_trip_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //tripList.add("Trip "+(tripList.size()+1));
                //adapter.notifyDataSetChanged();
                //showNewTripDialog();
                startActivity(new Intent(getApplicationContext(), NewTrip.class));
            }
        });

        dropDownOptions.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sortOptions));

        more = findViewById(R.id.icon_more);

    }

    @Override
    public void menu_reset(MenuItem item){
        showNewDialog("Trips");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.v("Wanderlust", "MyTrips: onResume()");
        trips.clear();
        trips = app.dbManager.getAll();
        adapter.notifyDataSetChanged();
    }

    public void showNewDialog(String origin) {
        // Create an instance of the dialog fragment and show it
        NewResetConfirmDialog dialog = new NewResetConfirmDialog();
        dialog.setOrigin(origin);
        dialog.show(getFragmentManager(), "TEST");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.v("Wanderlust", "reset approved - myTrips class");
        switch(((NewResetConfirmDialog)dialog).origin){
            case "Trips":
                app.dbManager.reset();
                break;
            case "SingleTrip":
                app.dbManager.deleteTrip(single_trip_id);
                single_trip_id = -1;
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Log.v("Wanderlust", "reset canceled - myTrips class");
    }

    public void showMenu (View view)
    {
        single_trip_id = ((ListView)view.getParent().getParent()).getPositionForView(view);
        Log.v("Wanderlust", ""+single_trip_id);
        PopupMenu menu = new PopupMenu (this, view);
        menu.inflate (R.menu.options_menu_layout);
        menu.show();
    }

    public void option_delete(MenuItem item){
        Log.i ("Wanderlust", "option_delete");
        showNewDialog("SingleTrip");
    }
    public void option_edit(MenuItem item){
        Log.i ("Wanderlust", "option_edit");
    }

}

