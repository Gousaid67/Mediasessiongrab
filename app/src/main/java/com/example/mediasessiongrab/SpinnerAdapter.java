package com.example.mediasessiongrab;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context ctx;
    private String[] contentArray;
    private Drawable[] imageArray;

    public SpinnerAdapter(Context context, int resource, String[] objects,
                          Drawable[] imageArray) {
        super(context,  R.layout.spinner_value_layout, R.id.spinnerTextView, objects);
        this.ctx = context;
        this.contentArray = objects;
        this.imageArray = imageArray;
    }

    @Override
    public View getDropDownView(int position, View convertView,ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_value_layout, parent, false);

        TextView textView = (TextView) row.findViewById(R.id.spinnerTextView);
        textView.setText(contentArray[position]);

        ImageView imageView = (ImageView)row.findViewById(R.id.spinnerImages);
        imageView.setImageDrawable(imageArray[position]);

        return row;
    }
}
