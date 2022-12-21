package com.example.booklibrary;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonStringToAuthorObject {
    public static List<Author> parseJsonToAuthor(String jsonString)
            throws JsonMappingException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonString);

        // Access the data in the JSON object
        int numFound = root.get("numFound").asInt();
        int start = root.get("start").asInt();
        boolean numFoundExact = root.get("numFoundExact").asBoolean();
        List<Author> ret = new ArrayList<Author>();
        // Access the array of documents
        JsonNode docs = root.get("docs");
        for (JsonNode doc : docs) {
            Author add = new Author();
            if (doc.get("key") != null) {
                String key = doc.get("key").asText();
                add.setKey(key);
            }
            if (doc.get("type") != null) {
                String type = doc.get("type").asText();
                add.setType(type);
            }
            if (doc.get("name") != null) {
                String name = doc.get("name").asText();
                add.setName(name);
            }
            // Access the array of alternate names
            if (doc.get("alternate_names") != null) {
                JsonNode alternateNames = doc.get("alternate_names");
                List<String> temp = new ArrayList<String>();
                for (JsonNode alternateName : alternateNames) {
                    String alternate = alternateName.asText();
                    temp.add(alternate);
                }
                add.setAlternateNames(temp);
            }
            if (doc.get("birth_date") != null) {
                String birthDate = doc.get("birth_date").asText();
                add.setBirthDate(birthDate);
            }
            if (doc.get("top_work") != null) {
                String topWork = doc.get("top_work").asText();
                add.setTopWork(topWork);
            }
            if (doc.get("work_count") != null) {
                int workCount = doc.get("work_count").asInt();
                add.setWorkCount(workCount);
            }
            // Access the array of top subjects
            if (doc.get("top_subjects") != null) {
                JsonNode topSubjects = doc.get("top_subjects");
                List<String> temp = new ArrayList<String>();
                for (JsonNode topSubject : topSubjects) {
                    String subject = topSubject.asText();
                    temp.add(subject);
                }
                add.setTopSubjects(temp);
            }
            ret.add(add);

        }
        return ret;
    }
}
