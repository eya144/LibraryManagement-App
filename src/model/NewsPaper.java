package model;



public class NewsPaper  extends Document{
    private String printdate;

    public NewsPaper(int id, String title, String printdate) {
        super(id, title);
        this.printdate = printdate;
    }

    public String getPrintdate() {
        return this.printdate;
    }

    public void setPrintdate(String printDate) {
        this.printdate = printDate;
    }

    @Override
    public String toString() {
        return "NewsPaper [printdate=" + this.printdate + "]";
    }
    
}
