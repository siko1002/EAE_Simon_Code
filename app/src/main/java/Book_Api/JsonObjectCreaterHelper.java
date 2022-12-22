package Book_Api;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class JsonObjectCreaterHelper {
    public static List<String> interpreteStringList(JsonNode doc, String attrName) {
        List<String> tempList = new ArrayList<String>();
        if (doc.get(attrName) != null) {
            JsonNode tempNode = doc.get(attrName);
            for (JsonNode tempIter : tempNode) {
                String tempString = tempIter.asText();
                tempList.add(tempString);
            }
        }
        return tempList;
    }

    public static String interpreteString(JsonNode doc, String attrName) {
        if (doc.get(attrName) != null) {
            return doc.get(attrName).asText();
        } else {
            return "";
        }
    }

    public static int interpreteInt(JsonNode doc, String attrName) {
        if (doc.get(attrName) != null) {
            return doc.get(attrName).asInt();
        } else {
            return 0;
        }
    }

    public static List<Integer> interpreteIntegerList(JsonNode doc, String attrName) {
        List<Integer> tempList = new ArrayList<Integer>();
        if (doc.get(attrName) != null) {
            JsonNode tempNode = doc.get(attrName);
            for (JsonNode tempIter : tempNode) {
                int tempString = tempIter.asInt();
                tempList.add(tempString);
            }
        }
        return tempList;
    }

    public static long interpreteLong(JsonNode doc, String attrName) {
        if (doc.get(attrName) != null) {
            return doc.get(attrName).asLong();
        } else {
            return 0;
        }
    }

    public static List<Long> interpreteLongList(JsonNode doc, String attrName) {
        List<Long> tempList = new ArrayList<Long>();
        if (doc.get(attrName) != null) {
            JsonNode tempNode = doc.get(attrName);
            for (JsonNode tempIter : tempNode) {
                long tempString = tempIter.asLong();
                tempList.add(tempString);
            }
        }
        return tempList;
    }

    public static boolean interpreteBoolean(JsonNode doc, String attrName) {
        if (doc.get(attrName) != null) {
            return doc.get(attrName).asBoolean();
        } else {
            return false;
        }
    }

    public static List<Boolean> interpreteBooleanList(JsonNode doc, String attrName) {
        List<Boolean> tempList = new ArrayList<Boolean>();
        if (doc.get(attrName) != null) {
            JsonNode tempNode = doc.get(attrName);
            for (JsonNode tempIter : tempNode) {
                boolean tempString = tempIter.asBoolean();
                tempList.add(tempString);
            }
        }
        return tempList;
    }
}
