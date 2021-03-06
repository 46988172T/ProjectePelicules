package com.example.leonardomartinez.peliculas;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.leonardomartinez.peliculas.API.Result;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Creamos un fragment ya que lo haremos compatible con tabletas Android.
 */
public class MainActivityFragment extends Fragment {
    private ListView moviesLw; // este es el ListView donde visualizaremos los datos.
    private ArrayList<Result> arrayMovies; //aqui guardaremos los datos.
    NouAdapter adaptador; // el adaptador para el ListView



    @Override
    public void onStart() {
        super.onStart();
        refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);

        //Binding del lvw de películas
        moviesLw = (ListView) fragment.findViewById(R.id.lista);

        //Creamos el arrayList de peliculas y lo llenamos con los datos por defecto
        arrayMovies = new ArrayList<>();

        //Creamos el adaptador y setteamos el ListView de películas, con esto 'traducimos' los datos para que sean visibles en el ListView
        adaptador = new NouAdapter(getContext(), R.layout.segundo, arrayMovies);
        moviesLw.setAdapter(adaptador);

        //Para una pulsación prolongada utilizamos este método. De momento no utilizada en esta aplicación,
        //puede ser util en caso de crear listas de favoritos (para poder borrar) o quizás alguna otra utilidad.
            /*moviesLw.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
                    arrayMovies.remove(position);
                    adaptador.notifyDataSetChanged();
                    return true;
                }
            });*/

        return fragment;//
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu, inflater);

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
        if (id == R.id.refresh) { //cuando le damos a refresh, el metodo refresh() que esta abajo se ejecuta
            refresh();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        MoviesApi pelicula = new MoviesApi();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        if (preferences.getString("example_list","0").equals("0")){ //si solicitamos ver las populares (0) nos las enseña (como en el onStart() )
            pelicula.showPopular(adaptador);
        }else if (preferences.getString("example_list","0").equals("1")) {//si solicitamos ver las más valoradas (1) nos las enseña (como en el onStart(), con el adaptador)
            pelicula.showTopRated(adaptador);
        }
    }
}

//a6023f741766a15020d9a29b13a476c8 api key