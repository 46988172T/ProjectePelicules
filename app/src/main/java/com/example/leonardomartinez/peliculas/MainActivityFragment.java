package com.example.leonardomartinez.peliculas;

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

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Creamos un fragment ya que lo haremos compatible con tabletas Android.
 */
public class MainActivityFragment extends Fragment {
    private ListView moviesLw; // este es el ListView donde visualizaremos los datos.
    private ArrayList<String> arrayMovies; //aqui guardaremos los datos.
    private ArrayAdapter<String> adaptador; // el adaptador para el ListView
    String pelis[] = {"Pelicula A", "Pelicula B", "Pelicula C", "Pelicula D", "Pelicula E", "Pelicula F", "Pelicula G", "Pelicula H",
            "Pelicula I", "Pelicula J"}; //datos por defecto


    @Override
    public void onStart() {
        super.onStart();
        MoviesApi pelicula = new MoviesApi();
        pelicula.Populares(adaptador);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View fragment = inflater.inflate(R.layout.fragment_main, container, false);

        //Binding del lvw de películas
        moviesLw = (ListView) fragment.findViewById(R.id.lista);

        //Creamos el arrayList de peliculas y lo llenamos con los datos por defecto
        arrayMovies = new ArrayList(Arrays.asList(pelis));

        //Creamos el adaptador y setteamos el ListView de películas, con esto 'traducimos' los datos para que sean visibles en el ListView
        adaptador = new ArrayAdapter<String>(getContext(), R.layout.segundo, R.id.textView, arrayMovies);
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

        return fragment;
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
        if (id == R.id.refresh) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

//a6023f741766a15020d9a29b13a476c8 api key