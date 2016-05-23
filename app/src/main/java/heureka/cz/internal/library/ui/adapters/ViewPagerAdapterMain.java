package heureka.cz.internal.library.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import heureka.cz.internal.library.ui.fragments.BookListFragment;
import heureka.cz.internal.library.ui.fragments.HeurekovinyListFragment;

/**
 * Created by Ondrej on 18. 5. 2016.
 */
public class ViewPagerAdapterMain extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 2;


    // Tab Titles
    private String tabtitles[] = new String[] { "Seznam knih", "Heurékoviny" };
    Context context;

    public ViewPagerAdapterMain(FragmentManager fm) {

        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {


            case 0:
                System.out.println("CASE0-BOOKLIST");
                BookListFragment bookList = new BookListFragment();
                return bookList;


            case 1:
                System.out.println("CASE1-HEUREKOVINY");
                HeurekovinyListFragment heurekaList = new HeurekovinyListFragment();
                return heurekaList;
        }
        return null;
    }

    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabtitles[position];
    }
}
