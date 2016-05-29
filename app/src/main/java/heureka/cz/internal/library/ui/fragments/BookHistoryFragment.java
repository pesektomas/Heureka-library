package heureka.cz.internal.library.ui.fragments;

/**
 * Created by Ondrej on 6. 5. 2016.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.helpers.CollectionUtils;
import heureka.cz.internal.library.helpers.Config;
import heureka.cz.internal.library.helpers.RetrofitBuilder;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.repository.BookHolders;
import heureka.cz.internal.library.repository.Settings;
import heureka.cz.internal.library.rest.ApiDescription;
import heureka.cz.internal.library.ui.MainActivity;
import heureka.cz.internal.library.ui.adapters.HistoryRecyclerAdapter;

public class BookHistoryFragment extends Fragment {

    @Inject
    RetrofitBuilder retrofitBuilder;

    @Inject
    CollectionUtils collectionUtils;

    @Inject
    Settings settings;

    @Bind(R.id.todo_list_view2)
    RecyclerView recyclerView;

    @Bind(R.id.neni_history)
    TextView tv;

    protected ApiDescription apiDescription;

    protected HistoryRecyclerAdapter adapter;

    public static final String TAG = "BookHistoryFragment";

    private Book bookDetail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_history, container, false);
        ((CodeCamp)getActivity().getApplication()).getApplicationComponent().inject(this);

        apiDescription = new ApiDescription(retrofitBuilder.provideRetrofit(settings.get() != null ? settings.get().getApiAddress() : Config.API_BASE_URL));
        ButterKnife.bind(this, view);
        return view;
    }


    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initAdapter(new ArrayList<BookHolders>());
        bookDetail = getActivity().getIntent().getExtras().getParcelable(MainActivity.KEY_BOOK_DETAIL);


        callApi();

    }
    protected void callApi() {

        apiDescription.historyOneBook(bookDetail.getBookId(), new ApiDescription.ResponseHandler() {
            @Override
            public void onResponse(Object data) {
                Log.d(TAG, "load books");

                ArrayList<BookHolders> list = (ArrayList<BookHolders>)data;
if(((ArrayList<BookHolders>) data).size()==0){
    System.out.println("NOT ELSE");
tv.setVisibility(View.VISIBLE);
}else{
    System.out.println("ELSE");
    tv.setVisibility(View.GONE);
}
                adapter.setData((ArrayList<BookHolders>) data);

            }

            @Override
            public void onFailure() {
                Log.d(TAG, "fail");
            }
        });
    }

    private void initAdapter(ArrayList<BookHolders> holders) {
        Log.d(TAG, "set adapter");

        if(getActivity() == null) {
            return;
        }

        adapter = new HistoryRecyclerAdapter(holders, collectionUtils);
        recyclerView.setAdapter(adapter);



        adapter.setListener(new HistoryRecyclerAdapter.OnTaskItemClickListener() {
            @Override
            public void onItemClick(int taskPosition) {
                Log.d(TAG, "on click");
                System.out.println();
                if(adapter.getHolders().get(taskPosition).getTextRate()!=null){
                    zobrazDialog(adapter, taskPosition);
                }

            }

            @Override
            public void onItemLongClick(int taskPosition) {
                Log.d(TAG, "on long click");
            }

            @Override
            public boolean onBackupClick(int taskPosition) {
                Log.d(TAG, "backup click");
//                try {
//                    adapter.getHolders().get(taskPosition).setDbTags(collectionUtils.implode(",", adapter.getHolders().get(taskPosition).getTags()));
//                    adapter.getBooks().get(taskPosition).save();
//                    return true;
//                } catch (Exception e) {
//                    Log.w(TAG, e);
//                    return false;
//                }
return false;
            }
        });
    }

public void zobrazDialog(HistoryRecyclerAdapter adapter, int position){
    // 1. Instantiate an AlertDialog.Builder with its constructor
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

// 2. Chain together various setter methods to set the dialog characteristics
    builder.setMessage(adapter.getHolders().get(position).getTextRate())
    .setTitle(adapter.getHolders().get(position).getName());

// 3. Get the AlertDialog from create()

    builder.setPositiveButton("Zavřít", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            // User clicked OK button
        }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
}



}