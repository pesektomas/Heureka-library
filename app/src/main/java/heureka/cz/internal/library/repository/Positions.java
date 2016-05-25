package heureka.cz.internal.library.repository;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tomas on 26.4.16.
 */
public class Positions implements Parcelable {

    public String id;
    public String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeValue(this.name);
    }

    public Positions() {
    }

    protected Positions(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
    }

    public static final Creator<Positions> CREATOR = new Creator<Positions>() {
        @Override
        public Positions createFromParcel(Parcel source) {
            return new Positions(source);
        }

        @Override
        public Positions[] newArray(int size) {
            return new Positions[size];
        }
    };

}
