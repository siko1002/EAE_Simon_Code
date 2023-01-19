package Book_Api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchResultStorage {
    int numFound;
    int start;
    boolean numFoundExact;
    //HashMap => (Buchtitel, Buch)
    HashMap<String ,Api_Book> books = new HashMap<String, Api_Book>();

    public void addBook(Api_Book book){
        this.books.put(book.getTitle(), book);
    }
    public Api_Book getBook(String title){
        return this.books.get(title);
    }

    public int getNumFound() {
        return numFound;
    }

    public void setNumFound(int numFound) {
        this.numFound = numFound;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public boolean isNumFoundExact() {
        return numFoundExact;
    }

    public void setNumFoundExact(boolean numFoundExact) {
        this.numFoundExact = numFoundExact;
    }

    public List<Api_Book> getBooks() {
        List<Api_Book> ret = new ArrayList<Api_Book>(books.values());
        return ret;
    }

    public void setBooks(HashMap<String, Api_Book> books) {
        this.books = books;
    }
}
