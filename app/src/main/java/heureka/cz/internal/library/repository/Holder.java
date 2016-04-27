package heureka.cz.internal.library.repository;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tomas on 26.4.16.
 */
public class Holder implements Parcelable {

    private String user;
    private String from;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.user);
        dest.writeString(this.from);
    }

    public Holder() {
    }

    protected Holder(Parcel in) {
        this.user = in.readString();
        this.from = in.readString();
    }

    public static final Creator<Holder> CREATOR = new Creator<Holder>() {
        @Override
        public Holder createFromParcel(Parcel source) {
            return new Holder(source);
        }

        @Override
        public Holder[] newArray(int size) {
            return new Holder[size];
        }
    };
}
