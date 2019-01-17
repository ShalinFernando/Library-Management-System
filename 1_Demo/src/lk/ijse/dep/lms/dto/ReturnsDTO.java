package lk.ijse.dep.lms.dto;

import java.sql.Date;
import java.time.LocalDate;

public class ReturnsDTO extends SuperDTO{
    private String borrowid;
    private String memberid;
    private String bookid;
    private LocalDate duedate;
    private LocalDate returndate;
    private int fine;

    public ReturnsDTO() {
    }

    public ReturnsDTO(String borrowid, String memberid, String bookid, LocalDate duedate, LocalDate returndate, int fine) {
        this.borrowid = borrowid;
        this.memberid = memberid;
        this.bookid = bookid;
        this.duedate = duedate;
        this.returndate = returndate;
        this.fine = fine;
    }

    public String getBorrowid() {
        return borrowid;
    }

    public void setBorrowid(String borrowid) {
        this.borrowid = borrowid;
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

    public LocalDate getDuedate() {
        return duedate;
    }

    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }

    public LocalDate getReturndate() {
        return returndate;
    }

    public void setReturndate(LocalDate returndate) {
        this.returndate = returndate;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }
}
