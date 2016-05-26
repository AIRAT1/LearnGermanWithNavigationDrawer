package de.android.learngermanwithnavigationdrawer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import de.android.learngermanwithnavigationdrawer.R;

public class MyArrayAdapter extends ArrayAdapter<String> {
    private final ArrayList<String> values;
//    alternative
//    private final Activity contextActivity;
    private Random random = new Random();
    private Context context;

//    alternative
//    public MyArrayAdapter(Activity contextActivity, ArrayList<String> values) {
//        super(contextActivity, R.layout.list_view_layout, values);
//        this.contextActivity = contextActivity;
//        this.values = values;
//    }
    public MyArrayAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.list_view_layout, values);
        this.context = context;
        this.values = values;
    }

    static class ViewHolder {
        public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        View view = convertView;
        if (view == null) {
//            alternative
//            LayoutInflater inflater = contextActivity.getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_layout, parent, false);
            holder = new ViewHolder();
            holder.textView = (TextView)view.findViewById(R.id.textViewListView);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        holder.textView.setText(values.get(position));
        holder.textView.setBackgroundColor(Color.argb(80, random.nextInt(255), random.nextInt(255),
                random.nextInt(255)));
        return view;
    }
}
