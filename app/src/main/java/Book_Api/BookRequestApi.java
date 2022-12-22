package Book_Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class BookRequestApi {
    public static List<Book> getBooks(String title) throws IOException {
        title.toLowerCase();
        title = title.replace(" ", "%20");

        String urlString = "https://openlibrary.org/search.json?title=" + title;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        String jsonString = content.toString();
        List<Book> books = JsonStringToBookObject.parseJsonToBook(jsonString);

        if (books.size() > 0) {
            return books;
        } else {
            return null;
        }

    }

    public static Book getBook(String title) throws IOException {
        title.toLowerCase();
        title = title.replace(" ", "%20");

        String urlString = "https://openlibrary.org/search.json?title=" + title;

        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        String jsonString = content.toString();
        List<Book> books = JsonStringToBookObject.parseJsonToBook(jsonString);

        if (books.size() > 0) {
            return books.get(0);
        } else {
            return null;
        }

    }
}