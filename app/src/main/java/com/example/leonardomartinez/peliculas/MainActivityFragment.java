package com.example.leonardomartinez.peliculas;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private ListView movies;
    private ArrayList<String> arraymovies;
    private ArrayAdapter<String> adaptador;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);

        movies = (ListView) fragment.findViewById(R.id.lista);
        arraymovies = new ArrayList<String>();

        adaptador = new ArrayAdapter<String>(getContext(),R.layout.segundo, R.id.textView,arraymovies);
        movies.setAdapter(adaptador);

        movies.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                arraymovies.remove(position);
                adaptador.notifyDataSetChanged();
                return true;
            }
        });





        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.fragment, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.refresh){

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

//a6023f741766a15020d9a29b13a476c8 api key