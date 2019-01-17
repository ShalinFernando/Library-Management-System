package lk.ijse.dep.lms.tablemodel;

public class BooksTM {
    private String bookid;
    private String bookname;
    private String bookcategory;

    public BooksTM() {
    }

    public BooksTM(String bookid, String bookname, String bookcategory) {
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
}
