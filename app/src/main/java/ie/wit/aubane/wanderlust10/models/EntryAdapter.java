package ie.wit.aubane.wanderlust10.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ie.wit.aubane.wanderlust10.R;

public class EntryAdapter extends ArrayAdapter<Entry> {
    private Context context;
    public List<Entry> entries;

    public EntryAdapter(Context context, List<Entry> entries)
    {
        super(context, R.layout.list_layout, entries);
        this.context   = context;
        this.entries = entries;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.list_layout, parent, false);
        Entry entry = entries.get(position);
        ((TextView)view.findViewById(R.id.list_item)).setText(entry.toString());

        return view;
    }

    @Override
    public int getCount()
    {
        return entries.size();
    }
}
