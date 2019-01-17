package lk.ijse.dep.lms.tablemodel;

import java.sql.Date;

public class ReturnsTM {
    private String memberid;
    private String bookid;
    private Date duedate;
    private Date returndate;
    private int fine;

    public ReturnsTM() {
    }

    public ReturnsTM(String memberid, String bookid, Date duedate, Date returndate, int fine) {
        this.memberid = memberid;
        this.bookid = bookid;
        this.duedate = duedate;
        this.returndate = returndate;
        this.fine = fine;
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

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }
}
