package model;

public class Document {
    private int id;
    private String title;


    public Document(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Document [id=" + this.id + ", title=" + this.title + "]";
    } 
     
}
