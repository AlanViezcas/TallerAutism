package com.example.lany.tallerautism;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ju on 21/03/2018.
 */

public class CustomAdapter extends ArrayAdapter<Tarjetas> {

    public CustomAdapter(@NonNull Context context, int resource, List<Tarjetas> tarjetaList) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //posicion es el lugar dela fila
        View vFila=convertView;//fila que se tiene que redibujar

        if(null == vFila) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vFila = inflater.inflate(R.layout.vista_superior, null);
        }
        Tarjetas product = getItem(position);
        ImageView img = (ImageView) vFila.findViewById(R.id.imgScreen1);


        img.setImageResource(product.getImageId());

        return vFila;
    }

}
