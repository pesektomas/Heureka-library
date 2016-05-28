package heureka.cz.internal.library.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.helpers.Config;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.rest.ApiDescription;

/**
 * Created by Alina on 07/05/2016.
 */
public class UserHistoryFragment extends AbstractBookFragment {
    private String user = "tomas";

    @Bind(R.id.nejsou_data)
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        ((CodeCamp)getActivity().getApplication()).getApplicationComponent().inject(this);
        apiDescription = new ApiDescription(retrofitBuilder.provideRetrofit(Config.API_BASE_URL));

        return v;
    }

    @Override
    protected void callApi() {
        apiDescription.getUserHistory(user,new ApiDescription.ResponseHandler() {
            @Override
            public void onResponse(Object data) {
                Log.d(TAG, "load books");
                adapter.setData((ArrayList<Book>) data);
                if(((ArrayList<Book>) data).size()==0){
                    System.out.println("NOT ELSE");
                    tv.setVisibility(View.VISIBLE);
                }else{
                    System.out.println("ELSE");
                    tv.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure() {
                Log.d(TAG, "fail");
            }
        });
    }

    @Override
    protected int getTitle() {
        return R.string.nav_user_history;
    }
}
