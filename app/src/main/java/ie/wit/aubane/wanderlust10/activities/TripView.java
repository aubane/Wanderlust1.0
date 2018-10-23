package ie.wit.aubane.wanderlust10.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ie.wit.aubane.wanderlust10.R;
import ie.wit.aubane.wanderlust10.models.Entry;
import ie.wit.aubane.wanderlust10.models.EntryAdapter;

public class TripView extends BaseClass {

    ListView listEntry;
    EntryAdapter adapter;
    int id;

    TextView title_trip, destination, start_date, end_date;
    Button edit_button, new_entry_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tripview);
        Log.v("Wanderlust", "Arrived in TripView Activity");

        Intent intent = getIntent();
        id = Integer.parseInt(intent.getStringExtra("tripID"));

        listEntry = findViewById(R.id.list_entries);
        adapter = new EntryAdapter(this, app.getEntries(id));
        listEntry.setAdapter(adapter);
        /*listEntry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(entryContent.get(position)!=null){
                    Toast.makeText(getApplicationContext(), entryContent.get(position).name, Toast.LENGTH_LONG).show();
                }
            }
        });*/
        edit_button = findViewById(R.id.edit_trip_button);
        new_entry_button = findViewById(R.id.new_entry_button);

        title_trip = findViewById(R.id.title_trip);
        destination = findViewById(R.id.destination);
        start_date = findViewById(R.id.start_date);
        end_date = findViewById(R.id.end_date);

        title_trip.setText(app.trips.get(id).name);
        destination.setText(app.trips.get(id).destination);
        start_date.setText(app.trips.get(id).start);
        end_date.setText(app.trips.get(id).end);
    }

    public void createNewEntry(View view){
        Intent intent = new Intent(getApplicationContext(), NewEntry.class);
        intent.putExtra("tripID", ""+id);
        startActivity(intent);
    }

    @Override
    public void menu_reset(MenuItem item){
        app.clearEntriesForTrip(id);
        adapter.notifyDataSetChanged();
        /*try{
            app.getSerializer().saveEntries((ArrayList)app.trips);
            Log.v("Wanderlust", "Wanderlust Trips JSON File Reset...");
            app.getSerializer().saveEntries((ArrayList)app.entries);
            Log.v("Wanderlust", "Wanderlust Entries JSON File Reset...");
        }
        catch (Exception e)
        {
            Log.v("Wanderlust", "Error Resetting Wanderlust... " + e.getMessage());
        }*/

    }
}
