package heureka.cz.internal.library.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.repository.BookHolders;
import heureka.cz.internal.library.rest.ApiDescription;
import heureka.cz.internal.library.ui.adapters.BookRecyclerAdapter;
import heureka.cz.internal.library.ui.adapters.ViewPagerAdapterMain;

/**
 * Created by Ondrej on 19. 5. 2016.
 */
public class ParentBookListFragment extends Fragment {
    //@Bind(R.id.pager3)
    //;



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        System.out.println("PARENT ON CREATEVIEW");
        View view = inflater.inflate(R.layout.fragment_parent, container, false);
        ((CodeCamp)getActivity().getApplication()).getApplicationComponent().inject(this);

        ButterKnife.bind(this, view);
        return view;
    }


    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("PARENT ON VIEXCREATED");
        ViewPager viewPager = (ViewPager)getView().findViewById(R.id.pager3);
        viewPager.setAdapter(new ViewPagerAdapterMain(getActivity().getSupportFragmentManager()));
            getActivity().setTitle(R.string.tit_books);


    }



}
