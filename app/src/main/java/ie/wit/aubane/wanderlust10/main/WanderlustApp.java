package ie.wit.aubane.wanderlust10.main;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ie.wit.aubane.wanderlust10.models.Entry;
import ie.wit.aubane.wanderlust10.models.Trip;
import ie.wit.aubane.wanderlust10.models.TripSerializer;

public class WanderlustApp extends Application {

    private TripSerializer serializer;
    public List<Trip> trips;
    public List<Entry> entries;
    public int amountTrips;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.v("Wanderlust", "Wanderlust App Started");
        /*serializer = new TripSerializer(this, "wanderlust.json");
        try{
            trips = serializer.loadTrips();
            Log.v("Wanderlust", "Wanderlust trips JSON file Created/Loaded");
        } catch(Exception e){
            Log.v("Wanderlust", "Error loading Wanderlust trips: "+e.getMessage());*/
            trips = new ArrayList<Trip>();
        //}
        amountTrips = trips.size();

        /*try{
            entries = serializer.loadEntries();
            Log.v("Wanderlust", "Wanderlust entries JSON file Created/Loaded");
        } catch(Exception e){
            Log.v("Wanderlust", "Error loading Wanderlust entries: "+e.getMessage());*/
            entries = new ArrayList<Entry>();
        //}
    }

    public void newTrip(Trip trip){
        trips.add(trip);
        amountTrips = trips.size();
        Log.v("Wanderlust", "New trip added. trips:"+trips.toString());
    }

    public void newEntry(int trip, Entry entry){
        entries.add(entry);
        trips.get(trip).addEntry(entry);
    }

    public List<Entry> getEntries(int trip){
        List<Entry> list = new ArrayList<Entry>();
        for(int i = 0; i<entries.size();i++){
            if(entries.get(i).trip_id == trip){
                list.add(entries.get(i));
            }
        }
        return list;
    }

    public void clearEntriesForTrip(int trip_id){
        trips.get(trip_id).entries.clear();
        for(int i = 0; i<entries.size();i++){
            if(entries.get(i).trip_id == trip_id){
                entries.remove(i);
            }
        }
    }

    /*public TripSerializer getSerializer() {
        return serializer;
    }*/
}
