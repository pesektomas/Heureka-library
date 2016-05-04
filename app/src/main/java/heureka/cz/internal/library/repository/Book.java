package heureka.cz.internal.library.repository;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tomas on 6.4.16.
 */

@Table(name = "book")
public class Book extends ParcelableModel implements Parcelable {

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "name")
    private String name;

    @Column(name = "detail_link")
    private String detailLink;

    @Column(name = "lang")
    private String lang;

    @Column(name = "form")
    private String form;

    @Column(name = "total")
    private Integer total;

    @Column(name = "tags")
    private String dbTags;

    private List<String> tags = new LinkedList<>();

    private ArrayList<Holder> holders = new ArrayList<>();

    private ArrayList<BookAvailable> available= new ArrayList<>();

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetailLink() {
        return detailLink;
    }

    public void setDetailLink(String detailLink) {
        this.detailLink = detailLink;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public ArrayList<Holder> getHolders() {
        return holders;
    }

    public void setHolders(ArrayList<Holder> holders) {
        this.holders = holders;
    }

    public ArrayList<BookAvailable> getAvailable() {
        return available;
    }

    public void setAvailable(ArrayList<BookAvailable> available) {
        this.available = available;
    }

    public String getDbTags() {
        return dbTags;
    }

    public void setDbTags(String dbTags) {
        this.dbTags = dbTags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.bookId);
        dest.writeString(this.name);
        dest.writeString(this.detailLink);
        dest.writeString(this.lang);
        dest.writeString(this.form);
        dest.writeString(this.dbTags);
        dest.writeValue(this.total);
        dest.writeStringList(this.tags);
        dest.writeTypedList(holders);
        dest.writeList(this.available);
    }

    public Book() {
    }

    protected Book(Parcel in) {
        this.bookId = in.readInt();
        this.name = in.readString();
        this.detailLink = in.readString();
        this.lang = in.readString();
        this.form = in.readString();
        this.dbTags = in.readString();
        this.total = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tags = in.createStringArrayList();
        this.holders = in.createTypedArrayList(Holder.CREATOR);
        this.available = new ArrayList<BookAvailable>();
        in.readList(this.available, BookAvailable.class.getClassLoader());
    }

    public static final Parcelable.Creator<Book> CREATOR = new Parcelable.Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel source) {
            return new Book(source);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };
}
