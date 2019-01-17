package lk.ijse.dep.lms.dto;

public class BooksDTO extends SuperDTO {
    private String bookid;
    private String bookname;
    private String bookcategory;

    public BooksDTO() {
    }

    public BooksDTO(String bookid, String bookname, String bookcategory) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.bookcategory = bookcategory;
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

    public String getBookcategory() {
        return bookcategory;
    }

    public void setBookcategory(String bookcategory) {
        this.bookcategory = bookcategory;
    }

    @Override
    public String toString() {
        return "BooksDTO{" +
                "bookid='" + bookid + '\'' +
                ", bookname='" + bookname + '\'' +
                ", bookcategory='" + bookcategory + '\'' +
                '}';
    }
}
