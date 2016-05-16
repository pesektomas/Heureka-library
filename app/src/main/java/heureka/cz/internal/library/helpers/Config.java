package heureka.cz.internal.library.helpers;

/**
 * Created by tomas on 26.4.16.
 */
public class Config {

    public static final String API_BASE_URL = "http://private-e52603-heurekalibrary.apiary-mock.com";

    public static final String URL_BOOKS = "/v1/books/";

    public static final String URL_BOOKS_MY = "/v1/books/my/{user}";

    public static final String URL_BOOKS_USER = "/v1/book/user-history/{user}";

    public static final String URL_BOOK = "/v1/book/{code}";

    public static final String URL_BORROW_BOOK = "/v1/book/borrow/{id}";

    public static final String URL_RESERVE_BOOK = "/v1/book/reservation/{id}";

    public static final String URL_RETURN_BOOK = "/v1/book/return/id/{id}/place/{place}";

}
