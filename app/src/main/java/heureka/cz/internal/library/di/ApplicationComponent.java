package heureka.cz.internal.library.di;

import javax.inject.Singleton;

import dagger.Component;
import heureka.cz.internal.library.ui.BookDetailActivity;
import heureka.cz.internal.library.ui.BookDetailAndResActivity;
import heureka.cz.internal.library.ui.BookReturnActivity;
import heureka.cz.internal.library.ui.fragments.UserHistoryFragment;
import heureka.cz.internal.library.ui.dialogs.SearchDialog;
import heureka.cz.internal.library.ui.fragments.BookDetailFragment;
import heureka.cz.internal.library.ui.fragments.BookHistoryFragment;
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
    void inject(BookDetailFragment bookDetailFragment);
    void inject(BookHistoryFragment bookHistoryFragment);
    void inject(UserHistoryFragment userHistoryFragment);

    // activity
    void inject(BookDetailActivity bookDetailFragment);
    void inject(BookDetailAndResActivity bookDetailAndResActivity);
    void inject(BookReturnActivity bookReturnActivity);

    // dialogs
    void inject(SearchDialog searchDialog);


}
