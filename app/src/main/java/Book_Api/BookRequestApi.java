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
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }
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

            SearchResultStorage searchResultStorage = new SearchResultStorage();
            String jsonString = content.toString();

           JsonStringToBookObject.parseJsonToBooks(jsonString, searchResultStorage);

            con.disconnect();
            return searchResultStorage;
        } catch (IOException e) {
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
}