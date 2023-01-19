package Book_Api;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklibrary.Api_Book_View;
import com.example.booklibrary.R;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookRequestApi extends AsyncTask<String, Void, SearchResultStorage> {
    private Context context;
    private ProgressDialog progressDialog;
    private Api_Book_Adapter api_book_adapter;

    public BookRequestApi(Context context) {
        this.context = context;
    }

    public BookRequestApi(Context context, Api_Book_Adapter api_book_adapter) {
        this.context = context; this.api_book_adapter = api_book_adapter;
    }

    @Override
    protected void onPreExecute() {
        //Process Dialog to show the User the App is loading
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    //Async Task to Request Books
    @Override
    protected SearchResultStorage doInBackground(String... params) {
        List<Api_Book> ret = new ArrayList<Api_Book>();
        String title = params[0];
        title = title.toLowerCase();
        title = title.replace(" ", "%20");
        Log.i("HSKL_API" , "BookRequestApi -> Title: " + title);
        String urlString = "https://openlibrary.org/search.json?title=" + title;
        Log.i("HSKL_API" , "BookRequestApi -> UrlString: " + urlString);

        progressDialog.setMessage(urlString);

        try {
            //Json Request
            URL url = new URL(urlString);
            Log.i("HSKL_TEST", urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            //Get JsonString
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();
            JSONObject jObject = new JSONObject(content.toString());

            //Persist JsonObjects in searchResultStorage
            SearchResultStorage searchResultStorage = new SearchResultStorage();
            searchResultStorage.setNumFound(jObject.getInt("numFound"));
            searchResultStorage.setStart(jObject.getInt("start"));
            searchResultStorage.setNumFoundExact(jObject.getBoolean("numFoundExact"));
            Log.i("HSKL_TEST", "Anzahl -> " + searchResultStorage.getNumFound());

           searchResultStorage = parseJsonToBooks(jObject, searchResultStorage);

            Log.i("HSKL_TEST", "srs size: " + searchResultStorage.getBooks().size());
            return searchResultStorage;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.i("HSKL_Api", "BookRequestApi -> Found nothing");

        return null;
    }



    @Override
    protected void onPostExecute(SearchResultStorage searchResultStorage) {
        progressDialog.dismiss();
        //super.onPostExecute(books);

        if(api_book_adapter != null) {
            api_book_adapter.addBooks(searchResultStorage.getBooks());
            //Experimentell -> Adapter soll sich erweitern
            api_book_adapter.notifyDataSetChanged();
        }
        //api_book_adapter.notifyDataSetChanged();
        if (searchResultStorage.getBooks() != null) {
            BookDBManager bookDBManager = new BookDBManager(context);
            for(Api_Book apiBook : searchResultStorage.getBooks()){
                bookDBManager.insert(apiBook);
            }
        } else {
            // No book was found
        }
    }

public static SearchResultStorage parseJsonToBooks(JSONObject jObject, SearchResultStorage searchResultStorage) {
    try {


        // set booklist values
        searchResultStorage.setNumFound(jObject.getInt("numFound"));;
        searchResultStorage.setStart(jObject.getInt("start"));
        searchResultStorage.setNumFoundExact(jObject.getBoolean("numFoundExact"));

        JSONArray jsonArray = jObject.getJSONArray("docs");
        //Json Object into Book Object
        for(int i = 0; i < jsonArray.length(); i++) {
            Log.i("HSKL_TEST", "" + jsonArray.length());
            JSONObject bookJson = (JSONObject) jsonArray.get(i);
            String key = bookJson.getString("key");
            String type = bookJson.getString("type");
            List<String> seed = getListFromJsonArray(bookJson.getJSONArray("seed"));
            String title = bookJson.getString("title");
            String titleSuggest = bookJson.getString("title_suggest");
            int editionCount = bookJson.getInt("edition_count");
            List<String> editionKey = getListFromJsonArray(bookJson.getJSONArray("edition_key"));
            List<String> publishDate = getListFromJsonArray(bookJson.getJSONArray("publish_date"));
            List<Integer> publishYear = getListFromIntArray(bookJson.getJSONArray("publish_year"));
            int firstPublishYear = bookJson.getInt("first_publish_year");
            int numberOfPagesMedian = bookJson.getInt("number_of_pages_median");

            List<String> publishPlace = getListFromJsonArray(bookJson.getJSONArray("publish_place"));

            List<String> contributor = getListFromJsonArray(bookJson.getJSONArray("contributor"));

            List<String> isbn = getListFromJsonArray(bookJson.getJSONArray("isbn"));
            List<String> authors = getListFromJsonArray(bookJson.getJSONArray("author_name"));
            List<String> subjects = getListFromJsonArray(bookJson.getJSONArray("subject"));
            String coverId = bookJson.getString("cover_i");
            byte[] coverImage = SaveImageFromUrl.saveImageToArray(coverId);

            //Parse Values into Book
            Api_Book add = new Api_Book(key, type, seed, title, titleSuggest, editionCount, editionKey, publishDate, publishYear,
                    firstPublishYear, numberOfPagesMedian, publishPlace, contributor, isbn, authors,
                    subjects, coverId, coverImage);
            Log.i("HSKL_TEST", add.toString());
            searchResultStorage.addBook(add);
        }
    } catch (JSONException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return searchResultStorage;
}
    //Create List from JsonDocs
    private static List<String> getListFromJsonArray(JSONArray array) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getString(i));
        }
        return list;
    }
    //Create List from JsonDocs
    private static List<Integer> getListFromIntArray(JSONArray array) throws JSONException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getInt(i));
        }
        return list;
    }
}