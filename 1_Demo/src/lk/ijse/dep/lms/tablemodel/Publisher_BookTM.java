package lk.ijse.dep.lms.tablemodel;

public class Publisher_BookTM {
    private String bookid;
    private String publisherid;


    public Publisher_BookTM() {
    }

    public Publisher_BookTM(String bookid, String publisherid) {
        this.bookid = bookid;
        this.publisherid = publisherid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(String publisherid) {
        this.publisherid = publisherid;
    }
}
