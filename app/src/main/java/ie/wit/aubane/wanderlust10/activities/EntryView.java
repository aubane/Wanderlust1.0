package ie.wit.aubane.wanderlust10.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import ie.wit.aubane.wanderlust10.R;

public class EntryView extends BaseClass {

    TextView entry_title, entry_text;
    int entry_id, trip_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entryview);
        Log.v("Wanderlust", "Arrived in EntryView Activity");

        Bundle extras = getIntent().getExtras();
        entry_id = Integer.parseInt(extras.getString("entryID"));
        trip_id = Integer.parseInt(extras.getString("tripID"));
        Log.v("Wanderlust", "EntryView: id passed from Intent: "+trip_id +", "+entry_id);

        entry_title = findViewById(R.id.entry_title);
        entry_text = findViewById(R.id.entry_text);

        entry_title.setText(app.trips.get(trip_id).entries.get(entry_id).name);
        entry_text.setText(app.trips.get(trip_id).entries.get(entry_id).text);
    }

    @Override
    public void menu_current_trip(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), TripView.class);
        Bundle extras = new Bundle();
        extras.putString("tripID", ""+trip_id);
        intent.putExtras(extras);
        startActivity(intent);
        finish();
    }
}
