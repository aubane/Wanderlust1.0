package ie.wit.aubane.wanderlust10.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ie.wit.aubane.wanderlust10.R;

public class TripAdapter extends ArrayAdapter<Trip>
{
    private Context context;
    public List<Trip> trips;

    public TripAdapter(Context context, List<Trip> trips)
    {
        super(context, R.layout.list_layout, trips);
        this.context   = context;
        this.trips = trips;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_layout, parent, false);
        Trip trip = trips.get(position);
        ((TextView)view.findViewById(R.id.list_item)).setText(trip.toString());

        return view;
    }

    @Override
    public int getCount()
    {
        return trips.size();
    }

}