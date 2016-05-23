package heureka.cz.internal.library.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.helpers.CollectionUtils;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.repository.Info;
import heureka.cz.internal.library.rest.ApiDescription;
import heureka.cz.internal.library.ui.adapters.AvailableRecyclerAdapter;
import heureka.cz.internal.library.ui.adapters.UsersRecyclerAdapter;
import retrofit2.Retrofit;

/**
 * Created by tomas on 27.4.16.
 */
public class BookDetailActivity extends AppCompatActivity {

    public static final String KEY_CAN_BORROW = "can_borrow";
    public static final String KEY_CAN_RESERVE = "can_reserve";
    public static final String MY_BOOK = "is_my_book";

    private Book bookDetail;

    /**
     * vypujcka by měla byt mozna jen po nacteni knihy cteckou,
     * aby nedoslo k vypojceni jine knihy
     * */
    private boolean canBorrow = false;
    private boolean canReturn = false;
    /**
     * zato rezervace kdykoliv jindy
     * */
    private boolean canReserve = false;

    private ApiDescription apiDescription;

    @Inject
    CollectionUtils collectionUtils;

    @Inject
    Retrofit retrofit;

    @Bind(R.id.coordinator)
    View coordinator;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.detail_name)
    TextView detailName;

    @Bind(R.id.tags)
    TextView detailTags;

    @Bind(R.id.form)
    TextView detailForm;

    @Bind(R.id.lang)
    TextView detailLang;

    @Bind(R.id.detail_link)
    TextView detailLink;

    @Bind(R.id.detail_available)
    RecyclerView detailAvailable;

    @Bind(R.id.detail_users)
    RecyclerView detailUsers;

    @Bind(R.id.btn_borrow)
    Button btnBorrow;

    @Bind(R.id.btn_reserve)
    Button btnReserve;


    @Bind(R.id.btn_return)
    Button btnReturn;

    @OnClick(R.id.btn_return)
    void returnBook() {
        btnReturn.setEnabled(false);
        Intent intent = new Intent(this, BookReturnActivity.class);
        Long bookId = bookDetail.getId();
        intent.putExtra("BOOK_ID", bookId);
        startActivity(intent);

//        apiDescription.returnBook(bookDetail.getBookId(), new ApiDescription.ResponseHandler() {
//            @Override
//            public void onResponse(Object data) {
//                Snackbar.make(coordinator, ((Info) data).getInfo(), Snackbar.LENGTH_SHORT).show();
//            }

//            @Override
//            public void onFailure() {
//                btnReturn.setEnabled(true);
//            }
//        });
//
    }


    @OnClick(R.id.btn_borrow)
    void borrowBook() {
        btnBorrow.setEnabled(false);
        apiDescription.borrowBook(bookDetail.getBookId(), new ApiDescription.ResponseHandler() {
            @Override
            public void onResponse(Object data) {
                Snackbar.make(coordinator, ((Info)data).getInfo(), Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                btnBorrow.setEnabled(true);
            }
        });
    }

    @OnClick(R.id.btn_reserve)
    void reserveBook() {
        btnReserve.setEnabled(false);
        apiDescription.reserveBook(bookDetail.getBookId(), new ApiDescription.ResponseHandler() {
            @Override
            public void onResponse(Object data) {
                Snackbar.make(coordinator, ((Info) data).getInfo(), Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                btnBorrow.setEnabled(true);
            }
        });
    }

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        ((CodeCamp)getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);

        // predani objektu z aktivity
        if (getIntent().getExtras() != null) {
            bookDetail = getIntent().getExtras().getParcelable(MainActivity.KEY_BOOK_DETAIL);
            canBorrow = getIntent().getExtras().getBoolean(KEY_CAN_BORROW);
            canReserve = getIntent().getExtras().getBoolean(KEY_CAN_RESERVE);
            canReturn = getIntent().getExtras().getBoolean(MY_BOOK);
        }

        // nacteni stavu po otoceni obrazovky
        if (savedInstanceState != null) {
            bookDetail = savedInstanceState.getParcelable(MainActivity.KEY_BOOK_DETAIL);
            canBorrow = savedInstanceState.getBoolean(KEY_CAN_BORROW);
            canReserve = savedInstanceState.getBoolean(KEY_CAN_RESERVE);
            canReturn = savedInstanceState.getBoolean(MY_BOOK);

        }

        apiDescription = new ApiDescription(retrofit);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(!canBorrow) {
            btnBorrow.getLayoutParams().height = 0;
        }

        if(!canReserve) {
            btnReserve.getLayoutParams().height = 0;
        }
        if(!canReturn){
            btnReturn.getLayoutParams().height = 0;
        }

        initBook();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MainActivity.KEY_BOOK_DETAIL, bookDetail);
        outState.putBoolean(KEY_CAN_BORROW, canBorrow);
        outState.putBoolean(KEY_CAN_RESERVE, canReserve);
        outState.putBoolean(MY_BOOK, canReturn);
    }

    private void initBook() {
        detailName.setText(bookDetail.getName());
        detailTags.setText(bookDetail.getTags().size() > 0 ? collectionUtils.implode(",", bookDetail.getTags()) : bookDetail.getDbTags());
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

}
