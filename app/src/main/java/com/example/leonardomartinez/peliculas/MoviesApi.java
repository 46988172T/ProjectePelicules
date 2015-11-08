package com.example.leonardomartinez.peliculas;



import com.example.leonardomartinez.peliculas.API.Example;
import com.example.leonardomartinez.peliculas.API.Result;

import android.widget.ArrayAdapter;
import java.util.ArrayList;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by 46988172t on 04/11/15.
 */
interface ServiceMovie {  //interfaz para obtener datos de Popular y TopRated

    @GET("popular") //gson solicitando la info de las peliculas populares
    Call<Example> Popular(
            @Query("api_key") String api_key);

    @GET("top_rated") //y otro gson para las más valoradas
    Call<Example> TopRated(
            @Query("api_key") String api_key);

}

public class MoviesApi { //aqui es donde creamos toda la conexion a la API..


    public static ServiceMovie apiService;
    public static String BASE_URL = "https://api.themoviedb.org/3/movie/"; //la URL de la api
    public static String API_ID = "a6023f741766a15020d9a29b13a476c8"; //nuestra api key para tener acceso a los datos

    public MoviesApi() { // Constructor:
        Retrofit retrofit = new Retrofit.Builder() // llamamos al buider de Retrofit...
                .baseUrl(BASE_URL) //...con la URL base de la api...
                .addConverterFactory(GsonConverterFactory.create()) //...llamamos al converter de Gson...
                .build(); //... y finalmente constrimos.
        apiService = retrofit.create(ServiceMovie.class); //creamos un servicio para que nos conecte con la api mediante los datos que
                                                            // le hemos indicado en la interfaz.
    }

    public void Populares(final ArrayAdapter<String> adapter) {

        Call<Example> getPelicula = apiService.Popular(API_ID); //llamada para que nos dé las peliculas más populares

        getPelicula.enqueue(new Callback<Example>() { //las ponemos en la cola...

            @Override
            public void onResponse(Response<Example> response, Retrofit retrofit) { //y en cuanto obtenemos respuesta, mediante el retrofit:

                ArrayList<String> peliDescripcion = new ArrayList<>(); //creamos un arrayList para guardar los datos de la pelicula
                Example pelicula = response.body(); //cada objeto de la respuesta será un objeto pelicula...

                for (Result list : pelicula.getResults()) { // ...que mediante este for vamos a recorrer...
                    String filmDescription = getFilmDescription(list); //...para obtener la descripcion
                    peliDescripcion.add(filmDescription); // y añadir el string al array.
                }

                adapter.clear();

                adapter.addAll(peliDescripcion);
            }

            @Override
            public void onFailure(Throwable t) {// necesario para que el callback no de errores. Informa de fallo

            }
        });
    }

    public void TopRated(final ArrayAdapter<String> adapter) {

        Call<Example> getPelicula = apiService.TopRated(API_ID);//llamada para que nos dé las peliculas más valoradas

        getPelicula.enqueue(new Callback<Example>() {//las ponemos en la cola...
            @Override
            public void onResponse(Response<Example> response, Retrofit retrofit) {//y en cuanto obtenemos respuesta, mediante el retrofit:

                ArrayList<String> peliDescripcion = new ArrayList<>(); //creamos un arrayList para guardar los datos de la película
                Example pelicula = response.body();//cada objeto de la respuesta será un objeto pelicula...

                for (Result list : pelicula.getResults() ){// ...que mediante este for vamos a recorrer...
                    String filmDescription = getFilmDescription(list);//...para obtener la descripcion
                    peliDescripcion.add(filmDescription);// y añadir el string al array.
                }
                adapter.clear();
                adapter.addAll(peliDescripcion);
            }

            @Override
            public void onFailure(Throwable t) { // necesario para que el callback no de errores. Informa de fallo

            }
        });
    }

    private String getFilmDescription(Result list) { //con este metodo recibimos toda la información de la película que necesitamos.


        String title = list.getTitle(); // título
        int popular = Math.round(list.getPopularity()); //la popularidad de la película
        String overview = list.getOverview(); //el resumen
        boolean adult = list.getAdult(); //si es recomendada o no para adultos/todas la edades
        String age; //creamos un string para que nos pase si es para mayores o no.

        if (adult) {
            age = "+18 years"; //si list.getAdult() es TRUE, recomendada será +18años
        } else {
            age = "All ages"; //en caso contrato, recomendada para todas las edades
        }

        return String.format("Title: %s\nSynopsis: %s\nRecommended: %s\nPopularity: %s\n",
                title, overview, age, popular
        ); //nos retorna un String con la información que necesitamos.
    }

}



