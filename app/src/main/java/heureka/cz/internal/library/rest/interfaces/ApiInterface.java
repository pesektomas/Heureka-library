package heureka.cz.internal.library.rest.interfaces;

import java.util.ArrayList;

import heureka.cz.internal.library.helpers.Config;
import heureka.cz.internal.library.repository.Book;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tomas on 26.4.16.
 */
public interface ApiInterface {

    @GET(Config.URL_BOOKS)
    Call<ArrayList<Book>> getBooks();

}
