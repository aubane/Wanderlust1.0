package ie.wit.aubane.wanderlust10.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import ie.wit.aubane.wanderlust10.R;
import ie.wit.aubane.wanderlust10.models.Trip;

public class NewTrip extends BaseClass {

    final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    NumberPicker startDay, startMonth, startYear, endDay, endMonth, endYear;
    EditText name, destination;
    Button cancel_button;
    String d, m, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtrip);

        setDate();

        startDay=findViewById(R.id.start_day);
        startMonth=findViewById(R.id.start_month);
        startYear=findViewById(R.id.start_year);

        endDay=findViewById(R.id.end_day);
        endMonth=findViewById(R.id.end_month);
        endYear=findViewById(R.id.end_year);

        startDay.setMinValue(01);
        startDay.setMaxValue(31);
        startDay.setValue(Integer.parseInt(d));

        startMonth.setMinValue(0);
        startMonth.setMaxValue(11);
        startMonth.setValue(Integer.parseInt(m));
        startMonth.setDisplayedValues(months);

        startYear.setMaxValue(2030);
        startYear.setMinValue(2017);
        startYear.setValue(Integer.parseInt(y));

        endDay.setMinValue(01);
        endDay.setMaxValue(31);
        endDay.setValue(Integer.parseInt(d)+1);

        endMonth.setMinValue(0);
        endMonth.setMaxValue(11);
        endMonth.setValue(Integer.parseInt(m));
        endMonth.setDisplayedValues(months);

        endYear.setMaxValue(2030);
        endYear.setMinValue(2017);
        endYear.setValue(Integer.parseInt(y));


        name = findViewById(R.id.new_trip_name);
        destination = findViewById(R.id.new_trip_destination);

        cancel_button = findViewById(R.id.new_trip_cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), MyTrips.class));
                finish();
            }
        });

    }

    public void createNewTrip(View view){
        String trip_name = name.getText().toString();
        if(trip_name.length()==0) trip_name = "New Trip";
        String trip_destination = destination.getText().toString();
        if(trip_destination.length()==0) trip_destination = "Unknown";
        String trip_start = startDay.getValue()+" "+months[startMonth.getValue()]+" "+startYear.getValue();
        String trip_end = endDay.getValue()+" "+ months[endMonth.getValue()]+" "+endYear.getValue();
        app.newTrip(new Trip(trip_name, trip_destination, trip_start, trip_end));
        startActivity(new Intent(this, MyTrips.class));
        //finish();
    }

    private void setDate(){
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(calendar.getTime());
        String date = formattedDate.split(" ")[0];
        y = date.split("-")[0];
        m = date.split("-")[1];
        d = date.split("-")[2];
        Log.v("Wanderlust", formattedDate);
    }
}
