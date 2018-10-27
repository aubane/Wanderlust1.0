package ie.wit.aubane.wanderlust10.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Trip {
    public static final String JSON_ID = "id";
    public static final String JSON_NAME = "name";
    public static final String JSON_DESTINATION = "destination";
    public static final String JSON_START = "start";
    public static final String JSON_END = "end";

    public long id;
    public String name;
    public String destination;
    public String start;
    public String end;
    public List<Entry> entries;

    public Trip(String name, String destination, String start, String end){
        this.id = unsignedLong();
        this.name=name;
        this.destination=destination;
        this.start=start;
        this.end=end;
        entries = new ArrayList<Entry>();

    }

    public Trip(JSONObject json) throws JSONException {
        id = json.getLong(JSON_ID);
        name = json.getString(JSON_NAME);
        destination = json.getString(JSON_DESTINATION);
        start = json.getString(JSON_START);
        end = json.getString(JSON_END);
        entries = new ArrayList<Entry>();
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_ID, id);
        json.put(JSON_NAME, name);
        json.put(JSON_DESTINATION, destination);
        json.put(JSON_START, start);
        json.put(JSON_END, end);
        return json;
    }

    public String toString(){
        return name+", Destination: "+destination;
    }

    /**
     * Generate a long greater than zero
     * @return Unsigned Long value greater than zero
     */
    private Long unsignedLong() {
        long rndVal = 0;
        do {
            rndVal = new Random().nextLong();
        } while (rndVal <= 0);
        return rndVal;
    }

    public void addEntry(Entry entry){
        entries.add(entry);
        Log.v("Wanderlust", "Trip: entry (hopefully) added:"+ entries.toString());
    }


}
