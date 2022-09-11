package model;

public class Magazine extends Document{
    private String field;
    public Magazine(int id, String title, String field) {
        super(id, title);
        this.field = field;
    }
    public String getField() {
        return this.field;
    }
    public void setField(String field) {
        this.field = field;
    }
    @Override
    public String toString() {
        return "Magazine [field=" + this.field + "]";
    }
    
}
