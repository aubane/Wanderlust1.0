package ie.wit.aubane.wanderlust10.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ie.wit.aubane.wanderlust10.R;
import ie.wit.aubane.wanderlust10.models.Entry;

public class NewEntry extends BaseClass {

    EditText entry_name;
    TextInputEditText entry_text;
    Button cancel_button, create_button;
    int trip_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newentry);
        Intent intent = getIntent();
        trip_id = Integer.parseInt(intent.getStringExtra("tripID"));

        entry_name = findViewById(R.id.new_entry_name);
        entry_text = findViewById(R.id.new_entry_text);

        cancel_button = findViewById(R.id.new_entry_cancel_button);
        create_button = findViewById(R.id.new_entry_create_button);

    }

    public void submitNewEntry(View view){
        String name = entry_name.getText().toString();
        if(name.length()==0) name = entry_name.getHint().toString();
        String text = entry_text.getText().toString();
        app.newEntry(trip_id, new Entry(trip_id, name, text));
        Intent i = new Intent(this, TripView.class);
        i.putExtra("tripID", ""+trip_id);
        startActivity(i);
        //finish();
    }
}
