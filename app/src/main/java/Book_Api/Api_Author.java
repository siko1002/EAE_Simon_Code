package Book_Api;
import java.util.List;

public class Api_Author {
    Api_Author() {
    }

    Api_Author(String key, String type, String name, List<String> alternateNames, String birthDate, String topWork,
               int workCount, List<String> topSubjects) {
        this.key = key;
        this.type = type;
        this.name = name;
        this.alternateNames = alternateNames;
        this.birthDate = birthDate;
        this.topWork = topWork;
        this.workCount = workCount;
        this.topSubjects = topSubjects;
    }

    String key;
    String type;
    String name;
    List<String> alternateNames;
    String birthDate;
    String topWork;
    int workCount;
    List<String> topSubjects;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(List<String> alternateNames) {
        this.alternateNames = alternateNames;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getTopWork() {
        return topWork;
    }

    public void setTopWork(String topWork) {
        this.topWork = topWork;
    }

    public int getWorkCount() {
        return workCount;
    }

    public void setWorkCount(int workCount) {
        this.workCount = workCount;
    }

    public List<String> getTopSubjects() {
        return topSubjects;
    }

    public void setTopSubjects(List<String> topSubjects) {
        this.topSubjects = topSubjects;
    }

    @Override
    public String toString() {
        return "Author: \nkey=" + key + ",\ntype=" + type + ",\nname=" + name + ",\nalternateNames=" + alternateNames
                + ",\nbirthDate=" + birthDate + ",\ntopWork=" + topWork + ",\nworkCount=" + workCount
                + ",\ntopSubjects=" + topSubjects + "\n";
    }

    public String toStringReduced() {
        StringBuilder sb = new StringBuilder();
        sb.append("Author [\n");

        if (key != null && key.length() > 0) {
            sb.append("\tkey=" + key + ",\n");
        }
        if (type != null && type.length() > 0) {
            sb.append("\ttype=" + type + ",\n");
        }
        if (name != null && name.length() > 0) {
            sb.append("\tname=" + name + ",\n");
        }
        if (alternateNames != null && alternateNames.size() > 0) {
            sb.append("\talternateNames=" + alternateNames + ",\n");
        }
        if (birthDate != null && birthDate.length() > 0) {
            sb.append("\tbirthDate=" + birthDate + ",\n");
        }
        if (topWork != null && topWork.length() > 0) {
            sb.append("\ttopWork=" + topWork + ",\n");
        }
        sb.append("\tworkCount=" + workCount + ",\n");
        if (topSubjects != null && topSubjects.size() > 0) {
            sb.append("\ttopSubjects=" + topSubjects + "\n");
        }
        sb.append("]\n");

        return sb.toString();
    }
}