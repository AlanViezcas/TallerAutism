package com.example.lany.tallerautism;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Lany on 19/03/2018.
 */

public class GridViewAdapter extends ArrayAdapter<Tarjetas>{

    public GridViewAdapter(@NonNull Context context, int resource,
                           @NonNull List<Tarjetas> objects) {
        super(context, resource, objects);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {
       // return super.getView(position, convertView, parent);
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_tarjetas, null);
        }
        Tarjetas product = getItem(position);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        TextView txtTitle = (TextView) v.findViewById(R.id.txtTitulo);


        img.setImageResource(product.getImageId());
        txtTitle.setText(product.getTitulo());

        return v;
    }
}
