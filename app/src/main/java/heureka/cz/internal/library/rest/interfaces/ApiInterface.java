package heureka.cz.internal.library.rest.interfaces;

import java.util.ArrayList;

import heureka.cz.internal.library.helpers.Config;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.repository.Info;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by tomas on 26.4.16.
 */
public interface ApiInterface {

    @GET(Config.URL_BOOKS)
    Call<ArrayList<Book>> getBooks();

    @GET(Config.URL_BOOK)
    Call<Book> getBook(@Path("code") String code);

    @POST(Config.URL_BORROW_BOOK)
    Call<Info> borrowBook(@Path("id") Integer id);

    @POST(Config.URL_RETURN_BOOK)
    Call<Info> returnBook(@Path("id") Integer id, @Path("place") String place);
}
