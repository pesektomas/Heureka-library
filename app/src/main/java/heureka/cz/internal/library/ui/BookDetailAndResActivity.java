package heureka.cz.internal.library.ui;

/**
 * Created by Ondrej on 8. 5. 2016.
 */
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.ui.adapters.ViewPagerAdapter;
import heureka.cz.internal.library.ui.dialogs.SearchDialog;

public class BookDetailAndResActivity extends AppCompatActivity{

    public static final String KEY_CAN_BORROW = "can_borrow";
    public static final String KEY_CAN_RESERVE = "can_reserve";
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.pager)
    ViewPager viewPager;

    private SearchDialog searchDialog = SearchDialog.newInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_detail_and_res);

        ((CodeCamp)getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setupToolbar();

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                FragmentManager fm = getSupportFragmentManager();
                searchDialog.show(fm, "fragment_search_dialog");
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
    }
}
