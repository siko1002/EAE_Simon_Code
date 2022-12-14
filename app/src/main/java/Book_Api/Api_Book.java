package Book_Api;

import java.util.Arrays;
import java.util.List;

public class Api_Book {
    public Api_Book() {

    }
    public Api_Book(String key, String type, List<String> seed, String title, String titleSuggest, int editionCount,
                    List<String> editionKey, List<String> publishDate, List<Integer> publishYear, int firstPublishYear,
                    int numberOfPagesMedian,List<String> publishPlace,
                    List<String> contributor, List<String> isbn, List<String> authors,
                    List<String> subjects, String coverId, byte[] coverImage){  this.key = key;
        this.type = type;
        this.seed = seed;
        this.title = title;
        this.titleSuggest = titleSuggest;
        this.editionCount = editionCount;
        this.editionKey = editionKey;
        this.publishDate = publishDate;
        this.publishYear = publishYear;
        this.firstPublishYear = firstPublishYear;
        this.numberOfPagesMedian = numberOfPagesMedian;

        this.publishPlace = publishPlace;

        this.contributor = contributor;

        this.isbn = isbn;
        this.authors = authors;
        this.subjects = subjects;
        this.coverId = coverId;
        this.coverImage = coverImage;}

    public Api_Book(String key, String type, List<String> seed, String title, String titleSuggest, int editionCount,
                    List<String> editionKey, List<String> publishDate, List<Integer> publishYear, int firstPublishYear,
                    int numberOfPagesMedian, List<String> lccn, List<String> publishPlace, List<String> oclc,
                    List<String> contributor, List<String> lcc, List<String> ddc, List<String> isbn, List<String> authors,
                    List<String> subjects, String coverId, byte[] coverImage) {
        this.key = key;
        this.type = type;
        this.seed = seed;
        this.title = title;
        this.titleSuggest = titleSuggest;
        this.editionCount = editionCount;
        this.editionKey = editionKey;
        this.publishDate = publishDate;
        this.publishYear = publishYear;
        this.firstPublishYear = firstPublishYear;
        this.numberOfPagesMedian = numberOfPagesMedian;

        this.publishPlace = publishPlace;

        this.contributor = contributor;

        this.isbn = isbn;
        this.authors = authors;
        this.subjects = subjects;
        this.coverId = coverId;
        this.coverImage = coverImage;
    }

    private String key;
    private String type;
    private String title;
    private String titleSuggest;
    private int editionCount;
    private int firstPublishYear;
    private int numberOfPagesMedian;

    private List<String> publishPlace;
    private List<String> contributor;
    private List<String> isbn;
    private List<String> authors;
    private List<String> subjects;
    private List<String> seed;
    private List<String> editionKey;
    private List<String> publishDate;
    private List<Integer> publishYear;

    private String coverId;
    private byte[] coverImage;

    public void addPublishPlace(String place){
        this.publishPlace.add(place);
    }
    public void addContributor(String contributor){
        this.contributor.add(contributor);
    }
    public void addISBN (String isbn){
        this.isbn.add(isbn);
    }
    public void addAuthor(String author){
        this.authors.add(author);
    }
    public void addSubjects(String subject){
        this.subjects.add(subject);
    }
    public void addSeed(String s){
        this.seed.add(s);
    }
    public void addEditionKey(String key){
        this.editionKey.add(key);
    }
    public void addPublishDate(String date){
        this.publishDate.add(date);
    }
    public void addPublishYear(int year){
        this.publishYear.add(year);
    }
    public String toStringReduced() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book [\n");

        if (key != null && key.length() > 0) {
            sb.append("\tkey=" + key + ",\n");
        }
        if (type != null && type.length() > 0) {
            sb.append("\ttype=" + type + ",\n");
        }
        if (seed != null && seed.size() > 0) {
            sb.append("\tseed=" + seed + ",\n");
        }
        if (title != null && title.length() > 0) {
            sb.append("\ttitle=" + title + ",\n");
        }
        if (titleSuggest != null && titleSuggest.length() > 0) {
            sb.append("\ttitleSuggest=" + titleSuggest + ",\n");
        }
        sb.append("\teditionCount=" + editionCount + ",\n");
        if (editionKey != null && editionKey.size() > 0) {
            sb.append("\teditionKey=" + editionKey + ",\n");
        }
        if (publishDate != null && publishDate.size() > 0) {
            sb.append("\tpublishDate=" + publishDate + ",\n");
        }
        if (publishYear != null && publishYear.size() > 0) {
            sb.append("\tpublishYear=" + publishYear + ",\n");
        }
        sb.append("\tfirstPublishYear=" + firstPublishYear + ",\n");
        sb.append("\tnumberOfPagesMedian=" + numberOfPagesMedian + ",\n");

        if (publishPlace != null && publishPlace.size() > 0) {
            sb.append("\tpublishPlace=" + publishPlace + ",\n");
        }

        if (contributor != null && contributor.size() > 0) {
            sb.append("\tcontributor=" + contributor + ",\n");
        }

        if (isbn != null && isbn.size() > 0) {
            sb.append("\tisbn=" + isbn + ",\n");
        }
        if (authors != null && authors.size() > 0) {
            sb.append("\tauthors=" + authors + ",\n");
        }
        if (subjects != null && subjects.size() > 0) {
            sb.append("\tsubjects=" + subjects + ",\n");
        }
        if (coverId != null && coverId.length() > 0) {
            sb.append("\tcoverId=" + coverId + ",\n");
        }
        if (coverImage != null) {
            sb.append("\tcoverImage=" + coverImage.toString() + ",\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Api_Book{" +
                "key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", titleSuggest='" + titleSuggest + '\'' +
                ", editionCount=" + editionCount +
                ", firstPublishYear=" + firstPublishYear +
                ", numberOfPagesMedian=" + numberOfPagesMedian +
                ", publishPlace=" + publishPlace +
                ", contributor=" + contributor +
                ", isbn=" + isbn +
                ", authors=" + authors +
                ", subjects=" + subjects +
                ", seed=" + seed +
                ", editionKey=" + editionKey +
                ", publishDate=" + publishDate +
                ", publishYear=" + publishYear +
                ", coverId='" + coverId + '\'' +
                ", coverImage=" + Arrays.toString(coverImage) +
                '}';
    }

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

    public List<String> getSeed() {
        return seed;
    }

    public void setSeed(List<String> seed) {
        this.seed = seed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleSuggest() {
        return titleSuggest;
    }

    public void setTitleSuggest(String titleSuggest) {
        this.titleSuggest = titleSuggest;
    }

    public int getEditionCount() {
        return editionCount;
    }

    public void setEditionCount(int editionCount) {
        this.editionCount = editionCount;
    }

    public List<String> getEditionKey() {
        return editionKey;
    }

    public void setEditionKey(List<String> editionKey) {
        this.editionKey = editionKey;
    }

    public List<String> getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(List<String> publishDate) {
        this.publishDate = publishDate;
    }

    public List<Integer> getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(List<Integer> publishYear) {
        this.publishYear = publishYear;
    }

    public int getFirstPublishYear() {
        return firstPublishYear;
    }

    public void setFirstPublishYear(int firstPublishYear) {
        this.firstPublishYear = firstPublishYear;
    }

    public int getNumberOfPagesMedian() {
        return numberOfPagesMedian;
    }

    public void setNumberOfPagesMedian(int numberOfPagesMedian) {
        this.numberOfPagesMedian = numberOfPagesMedian;
    }


    public List<String> getPublishPlace() {
        return publishPlace;
    }

    public void setPublishPlace(List<String> publishPlace) {
        this.publishPlace = publishPlace;
    }


    public List<String> getContributor() {
        return contributor;
    }

    public void setContributor(List<String> contributor) {
        this.contributor = contributor;
    }

    public List<String> getIsbn() {
        return isbn;
    }

    public void setIsbn(List<String> isbn) {
        this.isbn = isbn;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public byte[] getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }

    public String getCoverId() {
        return coverId;
    }

    public void setCoverId(String id) {
        this.coverId = id;
    }
    // Getters and setters for each field
}