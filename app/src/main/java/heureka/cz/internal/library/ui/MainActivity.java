package heureka.cz.internal.library.ui;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import butterknife.Bind;
import butterknife.ButterKnife;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.ui.dialogs.SearchDialog;
import heureka.cz.internal.library.ui.fragments.AbstractBookFragment;
import heureka.cz.internal.library.ui.fragments.BookListFragment;
import heureka.cz.internal.library.ui.fragments.MyBookListFragment;
import heureka.cz.internal.library.ui.fragments.MySavedBookFragment;

public class MainActivity extends AppCompatActivity implements AbstractBookFragment.BookDetailOpener, AbstractBookFragment.TitleSetter {

    public static final String TAG = "MainActivity";
    public static final String KEY_BOOK_DETAIL = "book_detail";

    private SearchDialog searchDialog = SearchDialog.newInstance();

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setupToolbar();
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideIme();
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.addDrawerListener(drawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        if (savedInstanceState == null) {
            createFragmentForActionId(R.id.nav_books);
        }

    }

    private void hideIme() {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void setupDrawerContent(NavigationView navigationView) {
        // Listener na vybirani menu itemu
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);

                        createFragmentForActionId(menuItem.getItemId());
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void createFragmentForActionId(int menuItem) {
        Fragment f = null;
        switch (menuItem) {
            case R.id.nav_books:
                f = new BookListFragment();
                break;
            case R.id.nav_my_books:
                f = new MyBookListFragment();
                break;
            case R.id.nav_saved_books:
                f = new MySavedBookFragment();
                break;
        }

        if (f != null) {
            removeOldFragments();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, f, f.getClass().getName())
                    .commit();
        }
    }

    private void removeOldFragments() {
        for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); i++) {
            getSupportFragmentManager().popBackStack();
        }
    }

    private void setupToolbar() {
        ActionBar ab = getSupportActionBar();
        ab.setDisplayShowTitleEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("???");
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

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            Log.d(TAG, "scan: " + scanResult.toString());

            FragmentManager fm = getSupportFragmentManager();
            searchDialog.setCode(scanResult.getContents());
            searchDialog.show(fm, "fragment_search_dialog");
        }
    }

    @Override
    public void bookDetail(Book book) {
        Intent intent = new Intent(this, BookDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_BOOK_DETAIL, book);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void setTitle(int titleId) {
        getSupportActionBar().setTitle(titleId);
    }

}
