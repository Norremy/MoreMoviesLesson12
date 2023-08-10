package sg.edu.rp.c346.id22030544.moremovieslesson12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> versionList;
    public CustomAdapter(Context context, int resource,
                         ArrayList<Movie> objects){
        super(context, resource, objects);
        this.parent_context = context;
        this.layout_id = resource;
        this.versionList = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewTitle);
        TextView tvGenre = rowView.findViewById(R.id.textViewGenre);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        ImageView iv = rowView.findViewById(R.id.imageView);
        // Obtain the Android Version information based on the position
        Movie currentVersion = versionList.get(position);
        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentVersion.getTitle());
        tvGenre.setText(currentVersion.getGenre());
//        tvYear.setText(currentVersion.getYear());
        tvYear.setText(String.valueOf(currentVersion.getYear()));
        if(currentVersion.getRating().equals("PG13")){
            iv.setImageResource(R.drawable.rating_pg13);
        }
        else if(currentVersion.getRating().equals("NC16")){
            iv.setImageResource(R.drawable.rating_nc16);
        }
        else {
            iv.setImageResource(R.drawable.rating_m18);
        }
        return rowView;
    }

}
