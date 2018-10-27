package ie.wit.aubane.wanderlust10.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
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
    TextView title;
    EditText name, destination;
    EditText start_date, end_date;
    Button cancel_button;
    String date, d, m, y;
    DatePickerDialog.OnDateSetListener listener_start, listener_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newtrip);

        setDate();

        listener_start = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                y = ""+year;
                m = ""+month;
                d = ""+dayOfMonth;

                if(m.equals("11")) m = "1";
                else m = (Integer.parseInt(m)+1)+"";

                start_date.setText(y + "-" + m + "-" + d);
                end_date.setText(y + "-" + m + "-" + d);
            }
        };

        listener_end = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int temp_month = month;
                if(temp_month == 11) temp_month = 1;
                else temp_month++;
                end_date.setText(year + "-" + temp_month + "-" + dayOfMonth);
            }
        };

        name = findViewById(R.id.new_trip_name);
        destination = findViewById(R.id.new_trip_destination);
        start_date = findViewById(R.id.start_date);
        start_date.setText(date);
        end_date = findViewById(R.id.end_date);
        end_date.setText(date);
        title = findViewById(R.id.new_trip_title);

        name.requestFocus();

        start_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.hasFocus();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NewTrip.this, listener_start, Integer.parseInt(y), Integer.parseInt(m), Integer.parseInt(d));
                datePickerDialog.show();
            }
        });

        end_date.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.hasFocus();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        NewTrip.this, listener_end, Integer.parseInt(y), Integer.parseInt(m), Integer.parseInt(d));
                datePickerDialog.show();
            }
        }));

        cancel_button = findViewById(R.id.new_trip_cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), MyTrips.class));
                finish();
            }
        });
    }

    public void createNewTrip(View view) {
        String trip_name = name.getText().toString();
        if (trip_name.length() == 0) trip_name = "New Trip";
        String trip_destination = destination.getText().toString();
        if (trip_destination.length() == 0) trip_destination = "Unknown";
        String trip_start = start_date.getText().toString();
        String trip_end = end_date.getText().toString();
        app.newTrip(new Trip(trip_name, trip_destination, trip_start, trip_end));
        finish();
    }

    private void setDate() {
        Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(calendar.getTime());
        date = formattedDate.split(" ")[0];
        y = date.split("-")[0];
        m = date.split("-")[1];
        d = date.split("-")[2];

        if(m=="1") m="11";
        else m = (Integer.parseInt(m)-1)+"";

        Log.v("Wanderlust", formattedDate);
    }

}

