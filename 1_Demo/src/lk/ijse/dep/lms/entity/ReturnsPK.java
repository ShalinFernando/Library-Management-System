package lk.ijse.dep.lms.entity;

public class ReturnsPK extends SuperEntity{
    private String memberid;
    private String bookid;

    public ReturnsPK() {
    }

    public ReturnsPK(String memberid, String bookid) {
        this.memberid = memberid;
        this.bookid = bookid;
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
}
