package com.example.leonardomartinez.peliculas;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ListView listaTareas;
    private ArrayList<String> arrayTareas;
    private Button añade;
    private EditText texto;
    private ArrayAdapter<String> adaptador;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);

        listaTareas = (ListView) fragment.findViewById(R.id.lista);
        arrayTareas = new ArrayList<String>();

        adaptador = new ArrayAdapter<String>(getContext(),R.layout.segundo, R.id.textView,arrayTareas);
        listaTareas.setAdapter(adaptador);

        listaTareas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                arrayTareas.remove(position);
                adaptador.notifyDataSetChanged();
                return true;
            }
        });

        //hola

        return fragment;
    }
}

