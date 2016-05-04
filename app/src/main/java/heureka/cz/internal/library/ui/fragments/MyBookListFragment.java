package heureka.cz.internal.library.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.helpers.CollectionUtils;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.rest.ApiDescription;
import heureka.cz.internal.library.ui.adapters.BookRecyclerAdapter;
import retrofit2.Retrofit;

/**
 * Created by tomas on 6.4.16.
 */
public class MyBookListFragment extends AbstractBookFragment {

    /**
     * TODO nacist z google?
     * */
    private String user = "tomas";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        ((CodeCamp)getActivity().getApplication()).getApplicationComponent().inject(this);
        apiDescription = new ApiDescription(retrofit);

        return v;
    }

    @Override
    protected void callApi() {
        apiDescription.getMyBooks(user, new ApiDescription.ResponseHandler() {
            @Override
            public void onResponse(Object data) {
                Log.d(TAG, "load books");
                adapter.setData((ArrayList<Book>) data);
            }

            @Override
            public void onFailure() {
                Log.d(TAG, "fail");
            }
        });
    }

    @Override
    protected int getTitle() {
        return R.string.tit_my_books;
    }
}
