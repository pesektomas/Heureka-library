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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import javax.inject.Inject;

import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.repository.Book;
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
        String code = "4";

        apiDescription.historyOneBook(code, new ApiDescription.ResponseHandler() {
            @Override
            public void onResponse(Object data) {
                Log.d(TAG, "load books");
                JSONObject jObject = (JSONObject)data;
                System.out.println("delka"+((JSONObject) data).length());
                try {
                    String aJsonString = jObject.getString("user_name");
                    System.out.println("delka"+((JSONObject) data).length());
                } catch (JSONException e) {
                    System.out.println("neni json neni nic");
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure() {
                Log.d(TAG, "fail");
            }
        });
    }

}