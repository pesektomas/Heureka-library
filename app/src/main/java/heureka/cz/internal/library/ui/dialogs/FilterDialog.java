package heureka.cz.internal.library.ui.dialogs;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;
import heureka.cz.internal.library.R;
import heureka.cz.internal.library.application.CodeCamp;
import heureka.cz.internal.library.helpers.RetrofitBuilder;
import heureka.cz.internal.library.ui.MainActivity;

/**
 * Created by Ondrej on 25. 5. 2016.
 */
public class FilterDialog extends DialogFragment {
    private static final String TAG = "FilterDialog";

    private String code = "";

    @Inject
    RetrofitBuilder retrofitBuilder;

    @Bind(R.id.chkCz)
    CheckBox chCz;

    @Bind(R.id.chkEn)
    CheckBox chEn;

    @Bind(R.id.chkBook)
    CheckBox chBook;

    @Bind(R.id.chkEbook)
    CheckBox chEbook;

    @Bind(R.id.chkAudioBook)
    CheckBox chAudiobook;





    @OnClick(R.id.filterit)
    void search() {
        System.out.println("ON CLICK FILTER");


        ((MainActivity)getActivity()).filter(chCz.isChecked(),chEn.isChecked(),chBook.isChecked(),chEbook.isChecked(),chAudiobook.isChecked());
        //getActivity().getFragmentManager().popBackStack();
        if(!FormValidator.validate(this, new SimpleErrorPopupCallback(getContext()))) {
            return;
        }




    }

    public FilterDialog() {
    }

    public static FilterDialog newInstance() {
        FilterDialog frag = new FilterDialog();
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_filter, container);

        ButterKnife.bind(this, view);
        ((CodeCamp)getActivity().getApplication()).getApplicationComponent().inject(this);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void onResume() {
        super.onResume();
        initWidth();
    }

    private void initWidth() {
        Window window = getDialog().getWindow();
        Point size = new Point();

        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);

        int width = size.x;

        window.setLayout((int) (width * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }

    public void setCode(String code) {
        this.code = code;
    }

}
