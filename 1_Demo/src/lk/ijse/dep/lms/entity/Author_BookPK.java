package lk.ijse.dep.lms.entity;

public class Author_BookPK extends SuperEntity {
    private String authorid;
    private String bookid;

    public Author_BookPK() {
    }

    public Author_BookPK(String authorid, String bookid) {
        this.authorid = authorid;
        this.bookid = bookid;
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
}
