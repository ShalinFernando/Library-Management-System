package lk.ijse.dep.lms.tablemodel;

public class Author_BookTM {
    private String bookid;
    private String authorid;


    public Author_BookTM() {
    }

    public Author_BookTM(String bookid, String authorid) {
        this.bookid = bookid;
        this.authorid = authorid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }
}
