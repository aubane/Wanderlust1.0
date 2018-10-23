package ie.wit.aubane.wanderlust10.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Date;

import ie.wit.aubane.wanderlust10.dialogs.DialogListener;
import ie.wit.aubane.wanderlust10.R;
import ie.wit.aubane.wanderlust10.models.Trip;
import ie.wit.aubane.wanderlust10.models.TripAdapter;

public class MyTrips extends BaseClass /*implements DialogListener*/{

    //DialogListener listener;
    public ListView listTrips;
    public TripAdapter adapter;
    public String[] sortOptions = {"name", "date", "location"};
    public Spinner dropDownOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytrips);
        Log.v("Wanderlust", "Arrived in MyTrips Activity");
        dropDownOptions = findViewById(R.id.sort_option);

        //set up the ListView for the trip list
        listTrips = findViewById(R.id.list_trips);
        adapter = new TripAdapter(this, app.trips );
        listTrips.setAdapter(adapter);
        //adds an onItemClickListener, forwards to activity TripView and sets the title correctly
        listTrips.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), tripList.get((int)id)+" clicked", Toast.LENGTH_SHORT).show();
                //title_trip.setText(tripList.get((int) id).toString());
                Log.v("Wanderlust", "Clicked on trip item");

                Intent intent = new Intent(getApplicationContext(), TripView.class);
                intent.putExtra("tripID", ""+position);
                startActivity(intent);

            }
        });

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
    }

    @Override
    public void menu_reset(MenuItem item){
        app.trips.clear();
        app.entries.clear();
        /*try{
            app.getSerializer().saveTrips((ArrayList)app.trips);
            Log.v("Wanderlust", "Wanderlust Trips JSON File Reset...");
            app.getSerializer().saveEntries((ArrayList)app.entries);
            Log.v("Wanderlust", "Wanderlust Entries JSON File Reset...");
        }
        catch (Exception e)
        {
            Log.v("Wanderlust", "Error Resetting Wanderlust... " + e.getMessage());
        }*/
        adapter.notifyDataSetChanged();
    }

    /*public void showNewTripDialog() {
        // Create an instance of the dialog fragment and show it
        DialogFragment dialog = new NewTripDialog();
        dialog.show(getFragmentManager(), "TEST");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        Log.v("Wanderlust", "newName["+newName+"]");
        if(newName.length()>0) {
            Log.v("Wanderlust", "input name");
            tripList.add(new Trip(newName));
        }else {
            Log.v("Wanderlust", "no input");
            tripList.add(new Trip("Trip " + (tripList.size() + 1)));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        Toast.makeText(getApplicationContext(), "nope", Toast.LENGTH_SHORT);
    }*/

}

