package heureka.cz.internal.library.repository;

import com.activeandroid.Model;

import java.lang.reflect.Field;

/**
 * Created by tomas on 27.4.16.
 */
public class ParcelableModel extends Model {

    public void setAaId(Long id) {
        try {
            Field idField = Model.class.getDeclaredField("mId");
            idField.setAccessible(true);
            idField.set(this, id);
        } catch (Exception e) {
            throw new RuntimeException("Reflection failed to get the Active Android ID", e);
        }
    }

}
