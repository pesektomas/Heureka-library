package heureka.cz.internal.library.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.helpers.Config;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.rest.ApiDescription;
import heureka.cz.internal.library.ui.MainActivity;
import heureka.cz.internal.library.ui.adapters.BookRecyclerAdapter;

/**
 * Created by tomas on 6.4.16.
 */
public class BookListFragment extends AbstractBookFragment {

    ArrayList<Book> books = null;
    boolean cz = true;
    boolean en = true;
    boolean book = true;
    boolean ebook = true;
    boolean audio = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        System.out.println("ONCREATEVIEW BLF");
        ((CodeCamp)getActivity().getApplication()).getApplicationComponent().inject(this);
        apiDescription = new ApiDescription(retrofitBuilder.provideRetrofit(settings.get() != null ? settings.get().getApiAddress() : Config.API_BASE_URL));
        return v;
    }

    @Override
    protected void callApi() {
        ArrayList<Book> finalBooks = new ArrayList<Book>();
        books = ((MainActivity) getActivity()).getBooks();
        if (books == null) {
            apiDescription.getBooks(new ApiDescription.ResponseHandler() {
                @Override
                public void onResponse(Object data) {
                    books = (ArrayList<Book>) data;


                    adapter.setData(books);
                    ((MainActivity) getActivity()).setBooks(books);
                }

                @Override
                public void onFailure() {
                    Log.d(TAG, "fail");
                }
            });
        } else {
            HashMap<String, Boolean> map = ((MainActivity) getActivity()).getType();
            cz = map.get("cz");
            en = map.get("en");
            book = map.get("book");
            ebook = map.get("ebook");
            audio = map.get("audio");


            for (int i = 0; i < books.size(); i++) {
                if ((cz == true && books.get(i).getLang().equals("Cesky")) || (en == true && books.get(i).getLang().equals("Anglicky"))) {
                    if ((book == true && books.get(i).getForm().equals("Papír")) || (ebook == true && books.get(i).getForm().equals("E-book")) || (audio == true && books.get(i).equals("Audio"))) {
                        System.out.println("ADD BOOK TO LIST" + books.get(i).getName().toString());
                        finalBooks.add(books.get(i));
                    }
                }
            }
            adapter.setData(finalBooks);
        }
    }



    @Override
    protected int getTitle() {
        return R.string.tit_books;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("ONRESUME LIST");
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.callSuper(view, savedInstanceState);
        System.out.println("ONVIEWCREATED BookList");



        if(getActivity() instanceof TitleSetter) {
            ((TitleSetter)getActivity()).setTitle(getTitle());
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        super.initAdapter(new ArrayList<Book>());

        if (savedInstanceState != null) {
            adapter.setData(savedInstanceState.<Book>getParcelableArrayList(KEY_BOOKS));
        } else {
            callApi();
        }
    }


}


