package lk.ijse.dep.lms.dto;

public class Publisher_BookDTOPK extends SuperDTO{

    private String bookid;
    private String publisherid;


    public Publisher_BookDTOPK() {
    }

    public Publisher_BookDTOPK(String bookid, String publisherid) {
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
