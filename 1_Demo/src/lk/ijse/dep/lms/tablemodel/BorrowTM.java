package lk.ijse.dep.lms.tablemodel;


import java.time.LocalDate;
import java.util.Date;

public class BorrowTM {
    private String memberid;
    private String bookid;
    private String authorid;
    private String publisherid;
    private LocalDate borrowdate;
    private LocalDate returndate;

    public BorrowTM() {
    }

    public BorrowTM(String memberid, String bookid, String authorid, String publisherid, LocalDate borrowdate, LocalDate returndate) {
        this.memberid = memberid;
        this.bookid = bookid;
        this.authorid = authorid;
        this.publisherid = publisherid;
        this.borrowdate = borrowdate;
        this.returndate = returndate;
    }



    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
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

    public String getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(String publisherid) {
        this.publisherid = publisherid;
    }

    public LocalDate getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(LocalDate borrowdate) {
        this.borrowdate = borrowdate;
    }

    public LocalDate getReturndate() {
        return returndate;
    }

    public void setReturndate(LocalDate returndate) {
        this.returndate = returndate;
    }
}
