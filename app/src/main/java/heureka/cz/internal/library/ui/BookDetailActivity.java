package heureka.cz.internal.library.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.helpers.CollectionUtils;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.repository.BookAvailable;
import heureka.cz.internal.library.ui.adapters.AvailableRecyclerAdapter;
import heureka.cz.internal.library.ui.adapters.BookRecyclerAdapter;
import heureka.cz.internal.library.ui.adapters.UsersRecyclerAdapter;

/**
 * Created by tomas on 27.4.16.
 */
public class BookDetailActivity extends AppCompatActivity {

    private Book bookDetail;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.detail_name)
    TextView detailName;

    @Bind(R.id.detail_tags)
    TextView detailTags;

    @Bind(R.id.detail_form)
    TextView detailForm;

    @Bind(R.id.detail_lang)
    TextView detailLang;

    @Bind(R.id.detail_link)
    TextView detailLink;

    @Bind(R.id.detail_available)
    RecyclerView detailAvailable;

    @Bind(R.id.detail_users)
    RecyclerView detailUsers;

    @OnClick(R.id.detail_link)
    void detailLink() {

        String url = bookDetail.getDetailLink();
        if(url == null) {
            return;
        }

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "http://" + url;
        }

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Inject
    CollectionUtils collectionUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ((CodeCamp)getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        // predani objektu z aktivity
        if (getIntent().getExtras() != null) {
            bookDetail = getIntent().getExtras().getParcelable(MainActivity.KEY_BOOK_DETAIL);
        }

        // nacteni stavu po otoceni obrazovky
        if (savedInstanceState != null) {
            bookDetail = savedInstanceState.getParcelable(MainActivity.KEY_BOOK_DETAIL);
        }

        //apiDescription = new ApiDescription(retrofit);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detailName.setText(bookDetail.getName());
        detailTags.setText(collectionUtils.implode(",", bookDetail.getTags()));
        detailLang.setText(bookDetail.getLang());
        detailForm.setText(bookDetail.getForm());
        detailLink.setText(bookDetail.getDetailLink());

        detailAvailable.setLayoutManager(new LinearLayoutManager(this));
        AvailableRecyclerAdapter adapterAvailable = new AvailableRecyclerAdapter(bookDetail.getAvailable());
        detailAvailable.setAdapter(adapterAvailable);

        detailUsers.setLayoutManager(new LinearLayoutManager(this));
        UsersRecyclerAdapter adapterUsers = new UsersRecyclerAdapter(bookDetail.getHolders());
        detailUsers.setAdapter(adapterUsers);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MainActivity.KEY_BOOK_DETAIL, bookDetail);
    }

}
