package com.example.leonardomartinez.peliculas.Service;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.leonardomartinez.peliculas.API.Example;
import com.example.leonardomartinez.peliculas.API.Result;
import java.util.ArrayList;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by 46988172t on 04/11/15.
 */
interface MovieService {  //interfaz para obtener datos de Popular y TopRated

    @GET("popular")
    Call<MovieService> Popular(
            @Query("api_key") String api_key);

    @GET("top_rated")
    Call<MovieService> TopRated(
            @Query("api_key") String api_key);


}

public class MoviesApi {


    public static MovieService apiService;
    public static String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public static String APPID = "a6023f741766a15020d9a29b13a476c8";

    public MoviesApi() { // Constructor
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(MovieService.class);
    }

    public void Populares(final ArrayAdapter<String> adapter) {

        Call<MovieService> getPelicula = apiService.Popular(APPID);

        getPelicula.enqueue(new Callback<MovieService>() {

            @Override
            public void onResponse(Response<MovieService> response, Retrofit retrofit) {


                ArrayList<String> peliDescripcion = new ArrayList<>();
                MovieService pelicula = response.body();

                for (Result list : pelicula.getResults()) {
                    String filmDescription = getFilmDescription(list);
                    peliDescripcion.add(filmDescription);
                }

                adapter.clear();

                adapter.addAll(peliDescripcion);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    public void Populares(final ArrayAdapter<String> adapter) {

        Call<MovieService> getPelicula = apiService.TopRated(APPID);

        getPelicula.enqueue(new Callback<MovieService>() {
            @Override
            public void onResponse(Response<MovieService> response, Retrofit retrofit) {

                ArrayList<String> peliDescripcion = new ArrayList<>();
                MovieService film = response.body();

                for (Result list : film.getResults()) {
                    String filmDescription = getFilmDescription(list);
                    peliDescripcion.add(filmDescription);
                }
                adapter.clear();
                adapter.addAll(peliDescripcion);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private String getFilmDescription(Result list) {

        String titulo = list.getTitle();
        int infoPopular = Math.round(list.getPopularity());
        String sinopsis = list.getOverview();
        boolean adulto = list.getAdult();
        String edadRecomendada;

        if (adulto) {
            edadRecomendada = "+ 18 years";
        } else {
            edadRecomendada = "- 18 years";
        }

        return String.format("Title: %s\nSynopsis: %s\nAdult: %s\nPopularity: %s\n",
                titulo, sinopsis, edadRecomendada, infoPopular
        );
    }

}



