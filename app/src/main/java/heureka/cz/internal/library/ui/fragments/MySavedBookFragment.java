package heureka.cz.internal.library.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

import heureka.cz.internal.library.R;
import heureka.cz.internal.library.repository.Book;

/**
 * Created by tomas on 4.5.16.
 */
public class MySavedBookFragment extends AbstractBookFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        //((CodeCamp)getActivity().getApplication()).getApplicationComponent().inject(this);

        return v;
    }

    @Override
    protected void callApi() {
        List<Book> data = new Select().from(Book.class).execute();
        adapter.setData(new ArrayList<Book>(data));
    }

    @Override
    protected int getTitle() {
        return R.string.tit_saved_books;
    }
}
