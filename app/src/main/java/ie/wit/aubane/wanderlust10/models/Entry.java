package ie.wit.aubane.wanderlust10.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Random;

public class Entry {

    /*public static final String JSON_ID = "id";
    public static final String JSON_NAME = "name";
    public static final String JSON_TEXT = "text";
    public static final String JSON_TRIP = "trip_id";*/

    public int id;
    public String name;
    public String content;
    public int trip_id;

    public Entry(){
        name="";
        content="";
        trip_id=-1;
    }
    public Entry(int trip_id, String name, String content){
        this.name=name;
        this.content=content;
        this.trip_id = trip_id;
    }

    public String toString(){
        return name;
    }
    /*
    public Entry(JSONObject json) throws JSONException {
        id = json.getLong(JSON_ID);
        name = json.getString(JSON_NAME);
        content = json.getString(JSON_TEXT);
        trip_id = json.getInt(JSON_TRIP);
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_ID, id);
        json.put(JSON_NAME, name);
        json.put(JSON_TEXT, text);
        json.put(JSON_TRIP, trip_id);
        return json;
    }
    */

    /**
     * Generate a long greater than zero
     * @return Unsigned Long value greater than zero
     *
    private Long unsignedLong() {
        long rndVal = 0;
        do {
            rndVal = new Random().nextLong();
        } while (rndVal <= 0);
        return rndVal;
    }
    */
}
