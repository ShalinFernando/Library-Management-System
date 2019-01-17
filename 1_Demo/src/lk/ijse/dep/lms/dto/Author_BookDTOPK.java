package lk.ijse.dep.lms.dto;

public class Author_BookDTOPK extends SuperDTO{
    private String authorid;
    private String bookid;

    public Author_BookDTOPK() {
    }

    public Author_BookDTOPK(String authorid, String bookid) {
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

    @Override
    public String toString() {
        return "Author_BookDTOPK{" +
                "authorid='" + authorid + '\'' +
                ", bookid='" + bookid + '\'' +
                '}';
    }
}
