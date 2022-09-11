package model;
public class Dictionary extends Document{
    private String author;

    public Dictionary(int id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Dictionary [author=" + this.author + "]";
    }
    
    
}
