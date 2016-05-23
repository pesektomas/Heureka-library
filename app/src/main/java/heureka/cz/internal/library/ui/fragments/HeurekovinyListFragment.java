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
import heureka.cz.internal.library.helpers.Config;
import heureka.cz.internal.library.helpers.RetrofitBuilder;
import heureka.cz.internal.library.repository.BookHolders;
import heureka.cz.internal.library.repository.Heurekoviny;
import heureka.cz.internal.library.repository.Settings;
import heureka.cz.internal.library.rest.ApiDescription;
import heureka.cz.internal.library.ui.adapters.HeurekaRecyclerAdapter;
import heureka.cz.internal.library.ui.adapters.HistoryRecyclerAdapter;
import retrofit2.Retrofit;

/**
 * Created by Ondrej on 18. 5. 2016.
 */
public class HeurekovinyListFragment extends Fragment{
    protected ApiDescription apiDescription;

    @Inject
    RetrofitBuilder retrofitBuilder;

    @Inject
    CollectionUtils collectionUtils;

    @Inject
    Settings settings;

    @Bind(R.id.todo_list_view3)
    RecyclerView recyclerView;

    protected HeurekaRecyclerAdapter adapter;
    public static final String TAG = "HeurekaListFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heurekoviny_list, container, false);

        ((CodeCamp)getActivity().getApplication()).getApplicationComponent().inject(this);

        apiDescription = new ApiDescription(retrofitBuilder.provideRetrofit(settings.get() != null ? settings.get().getApiAddress() : Config.API_BASE_URL));
        ButterKnife.bind(this, view);

        return view;
    }

    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initAdapter(new ArrayList<Heurekoviny>());
        callApi();

    }


    protected void callApi() {
        System.out.println("callapiheurekoviny");
        apiDescription.getHeurekoviny(new ApiDescription.ResponseHandler() {

            @Override
            public void onResponse(Object data) {
                Log.d(TAG, "load heurekoviny");
                adapter.setData((ArrayList<Heurekoviny>) data);
            }

            @Override
            public void onFailure() {
                Log.d(TAG, "fail");
            }
        });
    }

    private void initAdapter(ArrayList<Heurekoviny> heurekoviny) {
        Log.d(TAG, "set adapter");

        if(getActivity() == null) {
            return;
        }

        adapter = new HeurekaRecyclerAdapter(heurekoviny, collectionUtils);
        recyclerView.setAdapter(adapter);


    }
    protected int getTitle() {
        return R.string.tit_books;
    }

}
