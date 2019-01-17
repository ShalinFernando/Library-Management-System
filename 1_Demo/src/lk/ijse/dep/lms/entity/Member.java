package lk.ijse.dep.lms.entity;

public class Member extends SuperEntity{
    private String memberid;
    private String membername;
    private String email;

    public Member() {
    }

    public Member(String memberid, String membername, String email) {
        this.memberid = memberid;
        this.membername = membername;
        this.email = email;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
