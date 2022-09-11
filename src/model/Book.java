package model;

public class Book  extends Document{
	
	private String author;

	public Book(int id, String title, String author) {
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
        return "Book [author=" + author + "]";
    }

	
	
    
	
    
	

}
