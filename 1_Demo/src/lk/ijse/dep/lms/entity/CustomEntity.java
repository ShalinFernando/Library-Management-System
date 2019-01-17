package lk.ijse.dep.lms.entity;

public class CustomEntity{



    private String bookid;
    private String bookname;
    private String authorid;
    private String publisherid;

    public CustomEntity() {
    }

    public CustomEntity(String bookid, String bookname, String authorid, String publisherid) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.authorid = authorid;
        this.publisherid = publisherid;
    }

    public CustomEntity(String bookname, String authorid, String publisherid) {
        this.bookname = bookname;
        this.authorid = authorid;
        this.publisherid = publisherid;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(String publisherid) {
        this.publisherid = publisherid;
    }
}
