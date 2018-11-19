package ie.wit.aubane.wanderlust10.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ie.wit.aubane.wanderlust10.activities.BaseClass;
import ie.wit.aubane.wanderlust10.models.Entry;
import ie.wit.aubane.wanderlust10.models.Trip;

import static java.lang.Integer.parseInt;

public class DBManager {

    private SQLiteDatabase database;
    private DBDesigner dbHelper;

    public DBManager(Context context) {
        dbHelper = new DBDesigner(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public void add(Trip t) {
        ContentValues values = new ContentValues();
        values.put("name", t.name);
        values.put("destination", t.destination);
        values.put("start_date", t.start);
        values.put("end_date", t.end);

        database.insert("trips", null, values);
    }

    public void add(Entry e) {
        ContentValues values = new ContentValues();
        values.put("name", e.name);
        values.put("content", e.content);
        values.put("trip_id", e.trip_id);
        database.insert("entries", null, values);
    }

    public List<Trip> getAll() {
        List<Trip> trips = new ArrayList<Trip>();
        Cursor cursor = database.rawQuery("SELECT * FROM trips", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Trip t = toTrip(cursor);
            trips.add(t);
            cursor.moveToNext();
        }
        cursor.close();
        return trips;
    }

    public List<Entry> getAllEntries(){
        List<Entry> entries = new ArrayList<Entry>();
        Cursor cursor = database.rawQuery("SELECT * FROM entries", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Entry e = toEntry(cursor);
            entries.add(e);
            cursor.moveToNext();
        }
        cursor.close();
        return entries;
    }

    public List<Entry> getEntriesFromTrip(int trip_id){
        List<Entry> entries = new ArrayList<Entry>();
        Cursor cursor = database.rawQuery("SELECT * FROM entries WHERE trip_id = ?", new String[]{""+trip_id});
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Entry e = toEntry(cursor);
            entries.add(e);
            cursor.moveToNext();
        }
        cursor.close();
        return entries;
    }


    private Trip toTrip(Cursor cursor) {
        Trip pojo = new Trip();
        pojo.id = cursor.getInt(0);
        pojo.name = cursor.getString(1);
        pojo.destination = cursor.getString(2);
        pojo.start = cursor.getString(3);
        pojo.end = cursor.getString(4);
        return pojo;
    }

    private Entry toEntry(Cursor cursor) {
        Entry entry = new Entry();
        entry.id = cursor.getInt(0);
        entry.name = cursor.getString(1);
        entry.content = cursor.getString(2);
        entry.trip_id = parseInt(cursor.getString(3));
        return entry;
    }

    public void reset() {
        database.delete("trips", null, null);
        database.delete("entries", null, null);
    }

    public void deleteEntriesFromTrip(int trip_id){
        database.delete("entries", "trip_id =?", new String[]{""+trip_id});
    }

    public void deleteTrip(int trip_id){
        database.delete("trips", "trip_id =?", new String[]{""+trip_id});
        database.delete("entries", "trip_id =?", new String[]{""+trip_id});
    }

    public void deleteSingleEntry(int entry_id){
        database.delete("entries", "id=?", new String[]{""+entry_id});
    }

}
