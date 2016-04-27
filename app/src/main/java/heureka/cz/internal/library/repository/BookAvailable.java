package heureka.cz.internal.library.repository;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tomas on 26.4.16.
 */
public class BookAvailable implements Parcelable {

    private String place;
    private Integer quantity;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.place);
        dest.writeValue(this.quantity);
    }

    public BookAvailable() {
    }

    protected BookAvailable(Parcel in) {
        this.place = in.readString();
        this.quantity = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<BookAvailable> CREATOR = new Parcelable.Creator<BookAvailable>() {
        @Override
        public BookAvailable createFromParcel(Parcel source) {
            return new BookAvailable(source);
        }

        @Override
        public BookAvailable[] newArray(int size) {
            return new BookAvailable[size];
        }
    };

}
