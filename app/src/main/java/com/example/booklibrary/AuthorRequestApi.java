package com.example.booklibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import Book_Api.Api_Author;
import Book_Api.JsonStringToAuthorObject;

public class AuthorRequestApi {
    public static Api_Author getAuthor(String name) throws IOException {
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
        // System.out.println(jsonString);
        List<Api_Author> authoren = JsonStringToAuthorObject.parseJsonToAuthor(jsonString);
        /*
         * for (Author a : authoren) { System.out.println(a.toString()); }
         */
        if (authoren.size() > 0) {
            System.out.println(authoren.get(0));
            return authoren.get(0);
        } else
            return null;
    }

    public static List<Api_Author> getAuthorListByName(String name) throws IOException {
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
        // System.out.println(jsonString);
        List<Api_Author> authoren = JsonStringToAuthorObject.parseJsonToAuthor(jsonString);

        for (Api_Author a : authoren) {
            System.out.println(a.toString());
        }

        if (authoren.size() > 0) {
            System.out.println("Anzahl der Ergebnisse: " + authoren.size());
            return authoren;
        } else
            return null;
    }
}
