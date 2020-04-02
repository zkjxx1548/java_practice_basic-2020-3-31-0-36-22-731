package basicClass;

public class Subject {
    private String id;
    private String name;
    private String paperLink;

    public Subject() {
    }

    public Subject(String id, String name, String paperLink) {
        this.id = id;
        this.name = name;
        this.paperLink = paperLink;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPaperLink() {
        return paperLink;
    }
}
