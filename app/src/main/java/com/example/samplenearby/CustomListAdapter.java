package com.example.samplenearby;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter {

    private final Activity context;

    //to store the list of countries
    private final ArrayList<String> infoArray;

    public CustomListAdapter(Activity context, ArrayList<String> infoArrayParam ){

        super(context,R.layout.list_view_row , infoArrayParam);
        this.context=context;
        this.infoArray = infoArrayParam;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_view_row, null,true);

        TextView infoTextField = (TextView) rowView.findViewById(R.id.infoTextViewID);

        //this code sets the values of the objects to values from the arrays
        infoTextField.setText(infoArray.get(position));

        return rowView;

    };
}
