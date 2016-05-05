package heureka.cz.internal.library.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import heureka.cz.internal.library.application.CodeCamp;
import retrofit2.Retrofit;

/**
 * Created by Alina on 05/05/2016.
 */
public class UserHistoryActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO contentView

        ((CodeCamp)getApplication()).getApplicationComponent().inject(this);
        ButterKnife.bind(this);


    }
}
