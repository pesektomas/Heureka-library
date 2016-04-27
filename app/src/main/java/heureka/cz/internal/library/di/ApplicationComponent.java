package heureka.cz.internal.library.di;

import javax.inject.Singleton;

import dagger.Component;
import heureka.cz.internal.library.ui.BookDetailActivity;
import heureka.cz.internal.library.ui.fragments.BookListFragment;

/**
 * Created by tomas on 13.4.16.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BookListFragment taskFragment);
    void inject(BookDetailActivity bookDetailFragment);

}
