package lk.ijse.dep.lms.entity;

public class Author extends SuperEntity{
    private String authorid;
    private String authorname;
    private String authoremail;

    public Author() {
    }

    public Author(String authorid, String authorname, String authoremail) {
        this.authorid = authorid;
        this.authorname = authorname;
        this.authoremail = authoremail;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getAuthoremail() {
        return authoremail;
    }

    public void setAuthoremail(String authoremail) {
        this.authoremail = authoremail;
    }
}
