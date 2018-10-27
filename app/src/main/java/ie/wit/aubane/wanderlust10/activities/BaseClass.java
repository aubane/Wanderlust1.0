package ie.wit.aubane.wanderlust10.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import ie.wit.aubane.wanderlust10.R;
import ie.wit.aubane.wanderlust10.main.WanderlustApp;

public class BaseClass extends AppCompatActivity {

    WanderlustApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (WanderlustApp) getApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu (Menu menu){
        super.onPrepareOptionsMenu(menu);
        MenuItem my_trips = menu.findItem(R.id.menu_my_trips);
        MenuItem current_trip = menu.findItem(R.id.menu_current_trip);
        MenuItem reset = menu.findItem(R.id.menu_reset);

        if(this instanceof MyTrips){
            my_trips.setVisible(false);
            current_trip.setVisible(false);
            reset.setVisible(true);
            if(app.trips.size()>0){
                reset.setEnabled(true);
            }else{
                reset.setEnabled(false);
            }
        }
        else if(this instanceof TripView){
            my_trips.setVisible(true);
            current_trip.setVisible(false);
            reset.setVisible(true);
            if(app.getEntries(((TripView)this).trip_id).size()>0){
                reset.setEnabled(true);
            }else{
                reset.setEnabled(false);
            }
        }
        else if(this instanceof EntryView) {
            my_trips.setVisible(true);
            current_trip.setVisible(true);
            reset.setVisible(false);
        }
        else if(this instanceof NewTrip){
            my_trips.setVisible(true);
            current_trip.setVisible(false);
            reset.setVisible(false);
        }
        else if(this instanceof NewEntry){
            my_trips.setVisible(true);
            current_trip.setVisible(true);
            reset.setVisible(false);
        }

        return true;
    }

    public void menu_my_trips(MenuItem item)
    {
        startActivity (new Intent(this, MyTrips.class));
    }

    public void menu_current_trip(MenuItem item)
    {
        startActivity(new Intent(this, TripView.class));
    }

    public void menu_reset(MenuItem item){ }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("Wanderlust", this.getLocalClassName()+"Exiting Wanderlust... ");
        try {
            app.getSerializer().saveTrips((ArrayList)app.trips);
            app.getSerializer().saveEntries((ArrayList)app.entries);
            Log.v("Wanderlust", "Wanderlust JSON File Saved...");
        }
        catch (Exception e)
        {
            Log.v("Wanderlust", "Error Saving Wanderlust... " + e.getMessage());
        }
    }

}
