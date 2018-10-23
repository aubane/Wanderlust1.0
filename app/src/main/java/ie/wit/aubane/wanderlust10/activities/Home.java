package ie.wit.aubane.wanderlust10.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import ie.wit.aubane.wanderlust10.R;

public class Home extends BaseClass {

    Button my_trips_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        my_trips_button = findViewById(R.id.start_button);
    }

    public void myTripsButtonClicked(View view){
        Log.v("Wanderlust", "MyTrips Button clicked");
        startActivity(new Intent(this, MyTrips.class));
    }
}
