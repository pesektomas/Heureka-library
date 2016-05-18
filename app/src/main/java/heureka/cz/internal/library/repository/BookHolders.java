package heureka.cz.internal.library.repository;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ondrej on 9. 5. 2016.
 */
public class BookHolders {

   public String user_name;
    String type;
    String date;
    @SerializedName("return")
    String returnB;

    public String getBorrowDate(){
        return date;
    }

    public String getReturnDate(){
        return returnB;
    }

    public String getName(){
        return user_name;
    }

    public String getType(){
        return type;
    }
}
