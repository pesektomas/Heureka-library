package heureka.cz.internal.library.rest;

import java.lang.reflect.Array;
import java.util.ArrayList;

import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.repository.BookHolders;
import heureka.cz.internal.library.repository.Info;
import heureka.cz.internal.library.repository.Position;
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



    enum BookApi{
        BOOKS, MY_BOOKS
    }

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


    public void getPositions(final ResponseHandler responseHandler) {
        Call<ArrayList<Position>> call = apiInterface.getPositions();


        call.enqueue(new Callback<ArrayList<Position>>() {
            @Override
            public void onResponse(Call<ArrayList<Position>> call, Response<ArrayList<Position>> response) {
                if (response.isSuccessful()) {
                    responseHandler.onResponse(response.body());
                } else {
                    responseHandler.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Position>> call, Throwable t) {
                responseHandler.onFailure();
            }
        });
    }

    public void getMyBooks(String user, final ResponseHandler responseHandler) {
        Call<ArrayList<Book>> call = apiInterface.getMyBooks(user);
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

    public void getUserHistory(String user, final ResponseHandler responseHandler) {
        Call<ArrayList<Book>> call = apiInterface.getUserHistory(user);
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


    public void getBook(String bookCode, final ResponseHandler responseHandler) {
        Call<Book> call = apiInterface.getBook(bookCode);

        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()) {
                    responseHandler.onResponse(response.body());
                } else {
                    responseHandler.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                responseHandler.onFailure();
            }
        });
    }

    public void borrowBook(Integer bookId, final ResponseHandler responseHandler) {
        Call<Info> call = apiInterface.borrowBook(bookId);

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                if (response.isSuccessful()) {
                    responseHandler.onResponse(response.body());
                } else {
                    responseHandler.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                responseHandler.onFailure();
            }
        });
    }

    public void reserveBook(Integer bookId, final ResponseHandler responseHandler) {
        Call<Info> call = apiInterface.reserveBook(bookId);

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                if (response.isSuccessful()) {
                    responseHandler.onResponse(response.body());
                } else {
                    responseHandler.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                responseHandler.onFailure();
            }
        });
    }

    public void returnBook(int bookId, String user, String place, float rate, String rateText,final ResponseHandler responseHandler) {
        Call<Info> call = apiInterface.returnBook(bookId, user, place,rate, rateText);
        System.out.println("BOOK ID"+bookId+"URL"+call.request().url().toString());

        call.enqueue(new Callback<Info>() {
            @Override
            public void onResponse(Call<Info> call, Response<Info> response) {
                if (response.isSuccessful()) {
                    responseHandler.onResponse(response.body());
                } else {
                    responseHandler.onFailure();
                }
            }

            @Override
            public void onFailure(Call<Info> call, Throwable t) {
                responseHandler.onFailure();
            }
        });
    }

    public void historyOneBook(String bookCode, final ResponseHandler responseHandler){
        Call<ArrayList<BookHolders>> call = apiInterface.oneBookHistory(bookCode);
        System.out.println("BOOK CODE"+bookCode+"URL"+call.request().url().toString());

        call.enqueue(new Callback<ArrayList<BookHolders>>() {
            @Override
            public void onResponse(Call<ArrayList<BookHolders>> call, Response<ArrayList<BookHolders>> response) {


                    System.out.println("INRESPONSE"+response.message()+response.headers().toString());

                if (response.isSuccessful()) {
//                    ArrayList al =(ArrayList) response.body();
System.out.println("SUCESS");

                        System.out.println("RESPONSEBODY"+response.body().toString()+"json");

                    responseHandler.onResponse(response.body());
                } else {
                    responseHandler.onFailure();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BookHolders>> call, Throwable t) {
                responseHandler.onFailure();
            }

        });

    }
}
