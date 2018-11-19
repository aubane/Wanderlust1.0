package ie.wit.aubane.wanderlust10.main;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ie.wit.aubane.wanderlust10.database.DBManager;
import ie.wit.aubane.wanderlust10.models.Entry;
import ie.wit.aubane.wanderlust10.models.Trip;
import ie.wit.aubane.wanderlust10.models.TripSerializer;

public class WanderlustApp extends Application {

    //public List<Trip> trips;
    //public List<Entry> entries;

    public DBManager dbManager;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("Wanderlust", "Wanderlust App Started");
        dbManager = new DBManager(this);
        dbManager.open();
        Log.v("Wanderlust", "Wanderlust Database Opened");

    }

    public void newTrip(Trip trip){
        dbManager.add(trip);
        Log.v("Wanderlust", "New trip added. trips:"+dbManager.getAll().toString());
    }


    public void newEntry(Entry entry){
        dbManager.add(entry);
        Log.v("Wanderlust", "All entries:" +dbManager.getAllEntries().toString());
        Log.v("Wanderlust", "Trip entries:" +dbManager.getEntriesFromTrip(entry.trip_id).toString());
    }

}
