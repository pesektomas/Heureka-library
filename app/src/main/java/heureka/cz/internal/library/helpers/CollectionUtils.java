package heureka.cz.internal.library.helpers;

import android.util.Log;

import java.util.List;

/**
 * Created by tomas on 27.4.16.
 */
public class CollectionUtils {

    public String implode(String separator, String... data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length - 1; i++) {
            //data.length - 1 => to not add separator at the end
            if (!data[i].matches(" *")) {//empty string are ""; " "; "  "; and so on
                sb.append(data[i]);
                sb.append(separator);
            }
        }
        sb.append(data[data.length - 1].trim());
        return sb.toString();
    }

    public String implode(String separator, List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String item : list) {
            if (!item.matches(" *")) {//empty string are ""; " "; "  "; and so on
                sb.append(item);
                sb.append(separator);
            }
        }
        return sb.substring(0, (sb.length() - separator.length())).trim();
    }
}
