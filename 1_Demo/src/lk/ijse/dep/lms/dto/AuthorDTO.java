package lk.ijse.dep.lms.dto;

public class AuthorDTO extends SuperDTO {

    private String Authorid;
    private String authorname;
    private String authoremail;

    public AuthorDTO() {
    }

    public AuthorDTO(String authorid, String authorname, String authoremail) {
        Authorid = authorid;
        this.authorname = authorname;
        this.authoremail = authoremail;
    }

    public String getAuthorid() {
        return Authorid;
    }

    public void setAuthorid(String authorid) {
        Authorid = authorid;
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

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "Authorid='" + Authorid + '\'' +
                ", authorname='" + authorname + '\'' +
                ", authoremail='" + authoremail + '\'' +
                '}';
    }
}
