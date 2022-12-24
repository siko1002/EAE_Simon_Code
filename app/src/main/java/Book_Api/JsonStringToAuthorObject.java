package Book_Api;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStringToAuthorObject {
    public static List<Api_Author> parseJsonToAuthor(String jsonString)
            throws JsonMappingException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonString);

        // Access the data in the JSON object
        int numFound = root.get("numFound").asInt();
        int start = root.get("start").asInt();
        boolean numFoundExact = root.get("numFoundExact").asBoolean();
        List<Api_Author> ret = new ArrayList<Api_Author>();
        // Access the array of documents
        JsonNode docs = root.get("docs");
        for (JsonNode doc : docs) {
            Api_Author add = new Api_Author();
            // String key
            add.setKey(JsonObjectCreaterHelper.interpreteString(doc, "key"));
            // String type
            add.setType(JsonObjectCreaterHelper.interpreteString(doc, "type"));
            // String name
            add.setName(JsonObjectCreaterHelper.interpreteString(doc, "name"));
            // List<String> alternate_names
            add.setAlternateNames(JsonObjectCreaterHelper.interpreteStringList(doc, "alternate_names"));
            // String birth_date
            add.setBirthDate(JsonObjectCreaterHelper.interpreteString(doc, "birth_date"));
            // String top_work
            add.setTopWork(JsonObjectCreaterHelper.interpreteString(doc, "top_work"));
            // int work_count
            add.setWorkCount(JsonObjectCreaterHelper.interpreteInt(doc, "work_count"));
            // List<String> top_subjects
            add.setTopSubjects(JsonObjectCreaterHelper.interpreteStringList(doc, "top_subjects"));
            ret.add(add);

        }
        return ret;
    }
}