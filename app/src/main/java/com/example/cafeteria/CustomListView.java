package com.example.cafeteria;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomListView extends ArrayAdapter<String> {

    private ArrayList<String> Nombres;
    //private ArrayList<String> ima;
    private Activity context;

    public CustomListView(Activity context, ArrayList<String> Nombres/*, ArrayList<String> ima*/) {
        super(context, R.layout.listview_element, Nombres);
        this.context=context;
        this.Nombres = Nombres;
        //this.ima=ima;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;

        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_element, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) r.getTag();

        }
       /* String src= ima.get(position);

        Picasso.get()
                .load(src)
                .placeholder(null)
                .resize(250,350)
                .into(viewHolder.imageView);

        Picasso.get().load(src).into(viewHolder.imageView);*/
        viewHolder.textView.setText(Nombres.get(position));
        return r;
    }

     class ViewHolder{
        TextView textView;
        //ImageView imageView;
        ViewHolder(View view){
            textView = (TextView) view.findViewById(R.id.tvoptions);
            //imageView = (ImageView)view.findViewById(R.id.imageView);

        }
    }

}
