package com.example.leonardomartinez.peliculas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import com.example.leonardomartinez.peliculas.API.Result;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by 46988172t on 11/11/15.
 */
public class NouAdapter extends ArrayAdapter<Result> {
    final private String wwwPoster = "http://image.tmdb.org/t/p/";
    final private String sizePoster = "w185";
    DecimalFormat dec = new DecimalFormat("#.#");

    public NouAdapter(Context context, int resource, ArrayList<Result> objects) {
        super(context, resource, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Result item = getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.segundo, parent, false);
        }

        TextView tituloTv = (TextView) convertView.findViewById(R.id.titulo);
        TextView fechaTv = (TextView) convertView.findViewById(R.id.fecha);
        TextView percentTv = (TextView) convertView.findViewById(R.id.percent);
       // TextView tvDescripcion = (TextView) convertView.findViewById(R.id.description);//
        ImageView imagenPosterview = (ImageView) convertView.findViewById(R.id.imagenPoster);

        tituloTv.setText(item.getTitle());
        fechaTv.setText(item.getReleaseDate());
        percentTv.setText(dec.format(item.getPopularity())+"%");

        Picasso.with(getContext()).load(wwwPoster + sizePoster + item.getPosterPath()).fit().into(imagenPosterview);

        return convertView;
    }
}
