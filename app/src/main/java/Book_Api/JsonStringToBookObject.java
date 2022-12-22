package Book_Api;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStringToBookObject {
    public static List<Book> parseJsonToBook(String jsonString) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonString);

        // Access the data in the JSON object
        int numFound = root.get("numFound").asInt();
        int start = root.get("start").asInt();
        boolean numFoundExact = root.get("numFoundExact").asBoolean();
        List<Book> ret = new ArrayList<Book>();
        // Access the array of documents
        JsonNode docs = root.get("docs");
        for (JsonNode doc : docs) {
            Book add = new Book();
            // private String key;
            add.setKey(JsonObjectCreaterHelper.interpreteString(doc, "key"));
            // private String type;
            add.setType(JsonObjectCreaterHelper.interpreteString(doc, "type"));
            // private List<String> seed;
            add.setSeed(JsonObjectCreaterHelper.interpreteStringList(doc, "seed"));
            // private String title;
            add.setTitle(JsonObjectCreaterHelper.interpreteString(doc, "title"));
            // private String titleSuggest;
            add.setTitleSuggest(JsonObjectCreaterHelper.interpreteString(doc, "titleSuggest"));
            // private int editionCount;
            add.setEditionCount(JsonObjectCreaterHelper.interpreteInt(doc, "editionCount"));
            // private List<String> editionKey;
            add.setEditionKey(JsonObjectCreaterHelper.interpreteStringList(doc, "editionKey"));
            // private List<String> publishDate;
            add.setPublishDate(JsonObjectCreaterHelper.interpreteStringList(doc, "publishDate"));
            // private List<Integer> publishYear;
            add.setPublishYear(JsonObjectCreaterHelper.interpreteIntegerList(doc, "publishYear"));
            // private int firstPublishYear;
            add.setFirstPublishYear(JsonObjectCreaterHelper.interpreteInt(doc, "firstPublishYear"));
            // private int numberOfPagesMedian;
            add.setNumberOfPagesMedian(JsonObjectCreaterHelper.interpreteInt(doc, "numberOfPagesMedian"));
            // private List<String> lccn;
            add.setLccn(JsonObjectCreaterHelper.interpreteStringList(doc, "lccn"));
            // private List<String> publishPlace;
            add.setPublishDate(JsonObjectCreaterHelper.interpreteStringList(doc, "publishPlace"));
            // private List<String> oclc;
            add.setOclc(JsonObjectCreaterHelper.interpreteStringList(doc, "oclc"));
            // private List<String> contributor;
            add.setContributor(JsonObjectCreaterHelper.interpreteStringList(doc, "contributor"));
            // private List<String> lcc;
            add.setLcc(JsonObjectCreaterHelper.interpreteStringList(doc, "lcc"));
            // private List<String> ddc;
            add.setDdc(JsonObjectCreaterHelper.interpreteStringList(doc, "ddc"));
            // private List<String> isbn;
            add.setIsbn(JsonObjectCreaterHelper.interpreteStringList(doc, "isbn"));
            // private List<String> authors;
            add.setAuthors(JsonObjectCreaterHelper.interpreteStringList(doc, "authors"));
            // private List<String> subjects;
            add.setSubjects(JsonObjectCreaterHelper.interpreteStringList(doc, "subjects"));
            ret.add(add);
        }
        return ret;
    }
}