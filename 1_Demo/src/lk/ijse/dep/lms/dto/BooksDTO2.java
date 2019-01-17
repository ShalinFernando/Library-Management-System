package lk.ijse.dep.lms.dto;

public class BooksDTO2 extends SuperDTO{

    private String bookid;
    private String bookname;
    private String authorid;
    private String publisherid;


    public BooksDTO2() {
    }

    public BooksDTO2(String bookid,String bookname,String authorid,String publisherid) {

        this.bookid = bookid;
        this.bookname = bookname;
        this.authorid = authorid;
        this.publisherid = publisherid;



    }

    public BooksDTO2(String bookname,String authorid,String publisherid) {
        this.authorid = authorid;
        this.bookname = bookname;
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
