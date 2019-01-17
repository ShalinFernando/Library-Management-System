package lk.ijse.dep.lms.dto;

public class PublisherDTO extends SuperDTO{
    private String publisherid;
    private String publishername;
    private String publisheremail;

    public PublisherDTO() {
    }

    public PublisherDTO(String publisherid, String publishername, String publisheremail) {
        this.publisherid = publisherid;
        this.publishername = publishername;
        this.publisheremail = publisheremail;
    }

    public String getPublisherid() {
        return publisherid;
    }

    public void setPublisherid(String publisherid) {
        this.publisherid = publisherid;
    }

    public String getPublishername() {
        return publishername;
    }

    public void setPublishername(String publishername) {
        this.publishername = publishername;
    }

    public String getPublisheremail() {
        return publisheremail;
    }

    public void setPublisheremail(String publisheremail) {
        this.publisheremail = publisheremail;
    }

    @Override
    public String toString() {
        return "PublisherDTO{" +
                "publisherid='" + publisherid + '\'' +
                ", publishername='" + publishername + '\'' +
                ", publisheremail='" + publisheremail + '\'' +
                '}';
    }
}
