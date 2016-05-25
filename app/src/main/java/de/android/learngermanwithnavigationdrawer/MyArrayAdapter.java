package de.android.learngermanwithnavigationdrawer;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MyArrayAdapter extends ArrayAdapter<String> {
    private Context context;
    private ArrayList<String> values;
    private Random random = new Random();

    public MyArrayAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.list_view_layout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_view_layout, parent, false);
        TextView textViewListView = (TextView)view.findViewById(R.id.textViewListView);
        textViewListView.setText(values.get(position));
        textViewListView.setBackgroundColor(Color.argb(80, random.nextInt(255),
                random.nextInt(255), random.nextInt(255)));
        return view;
    }
}
