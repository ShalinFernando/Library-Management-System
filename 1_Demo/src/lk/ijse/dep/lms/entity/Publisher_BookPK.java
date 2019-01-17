package lk.ijse.dep.lms.entity;

public class Publisher_BookPK extends SuperEntity {
    private String publisherid;
    private String bookid;

    public Publisher_BookPK() {
    }

    public Publisher_BookPK(String publisherid, String bookid) {
        this.publisherid = publisherid;
        this.bookid = bookid;
    }

    public String getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(String publisherid) {
        this.publisherid = publisherid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }
}
