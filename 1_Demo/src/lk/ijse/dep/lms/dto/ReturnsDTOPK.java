package lk.ijse.dep.lms.dto;

public class ReturnsDTOPK extends SuperDTO{
    private String memberid;
    private String bookid;

    public ReturnsDTOPK() {
    }

    public ReturnsDTOPK(String memberid, String bookid) {
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

    @Override
    public String toString() {
        return "ReturnsDTOPK{" +
                "memberid='" + memberid + '\'' +
                ", bookid='" + bookid + '\'' +
                '}';
    }
}
