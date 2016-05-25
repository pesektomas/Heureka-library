package heureka.cz.internal.library.rest.interfaces;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.lang.reflect.Array;
import java.util.ArrayList;

import heureka.cz.internal.library.helpers.Config;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.repository.BookHolders;
import heureka.cz.internal.library.repository.Holder;
import heureka.cz.internal.library.repository.Info;
import heureka.cz.internal.library.repository.Position;
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

    @GET(Config.URL_BOOKS_MY)
    Call<ArrayList<Book>> getMyBooks(@Path("user") String user);

    @GET(Config.URL_BOOKS_USER)
    Call<ArrayList<Book>> getUserHistory(@Path("user") String user);

    @GET(Config.URL_BOOK)
    Call<Book> getBook(@Path("code") String code);

    @POST(Config.URL_RESERVE_BOOK)
    Call<Info> reserveBook(@Path("id") Integer id);

    @POST(Config.URL_BORROW_BOOK)
    Call<Info> borrowBook(@Path("id") Integer id);

    @POST(Config.URL_RETURN_BOOK)
    Call<Info> returnBook(@Path("id") int id, @Path("user") String user, @Path("place") String place, @Path("rate") float rate,@Path("ratetext") String rateText);

    @GET(Config.URL_ONE_BOOK_HISTORY)
    Call<ArrayList<BookHolders>> oneBookHistory(@Path("code") String code);

    @GET(Config.URL_POSITIONS)
    Call<ArrayList<Position>> getPositions();
}
