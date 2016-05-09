package heureka.cz.internal.library.ui.fragments;

/**
 * Created by Ondrej on 6. 5. 2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.repository.BookHolders;
import heureka.cz.internal.library.repository.Holder;
import heureka.cz.internal.library.rest.ApiDescription;
import retrofit2.Retrofit;

public class BookHistoryFragment extends Fragment {

    @Inject
    Retrofit retrofit;

    protected ApiDescription apiDescription;
    public static final String TAG = "BookHistoryFragment";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_history, container, false);
        ((CodeCamp)getActivity().getApplication()).getApplicationComponent().inject(this);
        apiDescription = new ApiDescription(retrofit);
        return view;
    }


    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callApi();
    }
    protected void callApi() {
        String code = "22";

        apiDescription.historyOneBook(code, new ApiDescription.ResponseHandler() {
            @Override
            public void onResponse(Object data) {
                Log.d(TAG, "load books");

                ArrayList<BookHolders> list = (ArrayList<BookHolders>)data;
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("list"+i+list.get(i).user_name);
                }


            }

            @Override
            public void onFailure() {
                Log.d(TAG, "fail");
            }
        });
    }

}