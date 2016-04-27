package heureka.cz.internal.library.rest;

import java.util.ArrayList;

import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.rest.interfaces.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by tomas on 26.4.16.
 */
public class ApiDescription {

    Retrofit retrofit;

    ApiInterface apiInterface;

    public interface ResponseHandler {
        public void onResponse(Object data);
        public void onFailure();
    }

    public ApiDescription(Retrofit retrofit) {
        this.retrofit = retrofit;
        this.apiInterface = retrofit.create(ApiInterface.class);
    }

    public void getBooks(final ResponseHandler responseHandler) {
        Call<ArrayList<Book>> call = apiInterface.getBooks();

        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                if (response.isSuccessful()) {
                    responseHandler.onResponse(response.body());
                } else {
                    responseHandler.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                responseHandler.onFailure();
            }
        });
    }
}
