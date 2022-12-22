package Book_Api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AuthorRequestApi {
    public static List<Author> getAuthoren(String name) throws IOException {
        name.toLowerCase();
        name = name.replace(" ", "%20");

        String urlString = "https://openlibrary.org/search/authors.json?q=" + name;

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
        List<Author> authoren = JsonStringToAuthorObject.parseJsonToAuthor(jsonString);
        if (authoren.size() > 0) {
            return authoren;
        } else
            return null;
    }

    public static Author getAuthor(String name) throws IOException {
        name.toLowerCase();
        name = name.replace(" ", "%20");

        String urlString = "https://openlibrary.org/search/authors.json?q=" + name;

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
        List<Author> authoren = JsonStringToAuthorObject.parseJsonToAuthor(jsonString);
        if (authoren.size() > 0) {
            return authoren.get(0);
        } else
            return null;
    }
}
