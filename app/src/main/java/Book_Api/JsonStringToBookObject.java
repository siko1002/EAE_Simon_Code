package Book_Api;

import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonStringToBookObject {
    public static Api_Book parseJsonToBook(String jsonString) {
        try {
            JSONObject bookJson = new JSONObject(jsonString).getJSONArray("docs").getJSONObject(0);

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
            List<String> lccn = getListFromJsonArray(bookJson.getJSONArray("lccn"));
            List<String> publishPlace = getListFromJsonArray(bookJson.getJSONArray("publish_place"));
            List<String> oclc = getListFromJsonArray(bookJson.getJSONArray("oclc"));
            List<String> contributor = getListFromJsonArray(bookJson.getJSONArray("contributor"));
            List<String> lcc = getListFromJsonArray(bookJson.getJSONArray("lcc"));
            List<String> ddc = getListFromJsonArray(bookJson.getJSONArray("ddc"));
            List<String> isbn = getListFromJsonArray(bookJson.getJSONArray("isbn"));
            List<String> authors = getListFromJsonArray(bookJson.getJSONArray("author_name"));
            List<String> subjects = getListFromJsonArray(bookJson.getJSONArray("subject"));
            String coverId = bookJson.getString("cover_i");
            byte[] coverImage = SaveImageFromUrl.saveImageToArray(coverId);

            return new Api_Book(key, type, seed, title, titleSuggest, editionCount, editionKey, publishDate, publishYear,
                    firstPublishYear, numberOfPagesMedian, lccn, publishPlace, oclc, contributor, lcc, ddc, isbn, authors,
                    subjects, coverId, coverImage);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    private static List<String> getListFromJsonArray(JSONArray array) throws JSONException {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getString(i));
        }
        return list;
    }
    private static List<Integer> getListFromIntArray(JSONArray array) throws JSONException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.getInt(i));
        }
        return list;
    }
}

