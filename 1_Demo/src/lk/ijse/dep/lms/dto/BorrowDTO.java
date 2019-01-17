package lk.ijse.dep.lms.dto;


import java.time.LocalDate;
import java.util.Date;

public class BorrowDTO extends SuperDTO {
    private String borrowid;
    private String bookid;
    private String memberid;
    private LocalDate borrowdate;
    private LocalDate returndate;

    public BorrowDTO() {
    }

    public BorrowDTO(String borrowid, String bookid, String memberid, LocalDate borrowdate, LocalDate returndate) {
        this.borrowid = borrowid;
        this.bookid = bookid;
        this.memberid = memberid;
        this.borrowdate = borrowdate;
        this.returndate = returndate;
    }

    public String getBorrowid() {
        return borrowid;
    }

    public void setBorrowid(String borrowid) {
        this.borrowid = borrowid;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
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

    @Override
    public String toString() {
        return "BorrowDTO{" +
                "borrowid='" + borrowid + '\'' +
                ", bookid='" + bookid + '\'' +
                ", memberid='" + memberid + '\'' +
                ", borrowdate=" + borrowdate +
                ", returndate=" + returndate +
                '}';
    }
}
