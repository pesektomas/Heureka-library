package heureka.cz.internal.library.ui.dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnItemSelected;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.repository.Book;
import heureka.cz.internal.library.repository.Info;
import heureka.cz.internal.library.repository.Position;
import heureka.cz.internal.library.rest.ApiDescription;
import heureka.cz.internal.library.ui.BookDetailActivity;
import heureka.cz.internal.library.ui.BookDetailAndResActivity;
import heureka.cz.internal.library.ui.MainActivity;
import retrofit2.Retrofit;

/**
 * Created by tomas on 26.4.16.
 */
public class RateDialog extends DialogFragment {
String user = "tomas";
    private static final String TAG = "RateDialog";

    private String position = "Praha";

    private ArrayList<Position> positions;
    private Info info;
    private List<String> myPositions;


    private ApiDescription apiDescription;


    @Inject
    Retrofit retrofit;

    @Bind(R.id.editText1)
    EditText commentText;



    @Bind(R.id.button)
    Button button1;
    @Bind(R.id.ratingBar)
    RatingBar ratingBar;

    //@Bind(R.id.coordinator1)
    //View coordinator;

    @Bind(R.id.spinner)
    Spinner spinner;

//    @OnFocusChange(R.id.editText1) void onFocusChanged(boolean focused) {
//if(focused) {
//    getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//}
//        else getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//    }

    @OnItemSelected(R.id.spinner) void onItemSelected(int position) {
   // System.out.println("Spinner Selected");
    }
    ArrayAdapter<String> spinnerArrayAdapter;
    int id;
    public RateDialog() {
    }

    public static RateDialog newInstance() {
        RateDialog frag = new RateDialog();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.dialog_rate, container, false);
        ButterKnife.bind(this,view);
        Bundle mArgs = getArguments();
        id = mArgs.getInt("bookId");
        ((CodeCamp) getActivity().getApplication()).getApplicationComponent().inject(this);

        myPositions = new ArrayList<>();

        apiDescription = new ApiDescription(retrofit);

      //  List<String> categories = new ArrayList<String>();
       // categories.add("Praha");
      //  categories.add("Liberec");
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

      //  adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     //   spinner.setAdapter(adapter);

        apiDescription.getPositions(new ApiDescription.ResponseHandler() {

            @Override
            public void onResponse(Object data) {
System.out.println("On positions response");

                ArrayList<Position> array = (ArrayList<Position>) data;
                for (Position pos : array) {
                    position = pos.getName();
                    myPositions.add(position);
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, myPositions);
                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(dataAdapter);
                }

            }

            @Override
            public void onFailure() {
                System.out.println("Positions fail");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentText.getText().toString();
                float ratingValue = ratingBar.getRating();
                position = spinner.getSelectedItem().toString();
                button1.setEnabled(false);
                // textView1.setText(comment + ratingValue + " id: " + id);

                apiDescription.returnBook(id, "tomas", position, ratingValue, comment, new ApiDescription.ResponseHandler() {
                    @Override
                    public void onResponse(Object data) {
                        getDialog().dismiss();
                    }

                    @Override
                    public void onFailure() {
                        System.out.println("Fail");
                        button1.setEnabled(true);
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

//        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


//        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
  //          public void onRatingChanged(RatingBar ratingBar, float rating,
   //                                     boolean fromUser) {
//
            }
  //      });
   // }

    public void onResume() {
        super.onResume();
    }



}
