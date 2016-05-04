package heureka.cz.internal.library.di;

import javax.inject.Singleton;

import dagger.Component;
import heureka.cz.internal.library.ui.BookDetailActivity;
import heureka.cz.internal.library.ui.dialogs.SearchDialog;
import heureka.cz.internal.library.ui.fragments.BookListFragment;
import heureka.cz.internal.library.ui.fragments.MyBookListFragment;

/**
 * Created by tomas on 13.4.16.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    // fragments
    void inject(BookListFragment taskFragment);
    void inject(MyBookListFragment taskFragment);

    // activity
    void inject(BookDetailActivity bookDetailFragment);

    // dialogs
    void inject(SearchDialog searchDialog);


}
