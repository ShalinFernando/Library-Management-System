package lk.ijse.dep.lms.dto;

public class Publisher_BookDTO extends SuperDTO{

    private Publisher_BookDTOPK publisher_bookDTOPK;
    private String bookname;
    private String publishername;

    public Publisher_BookDTO() {
    }


    public Publisher_BookDTO(String publisherid, String bookid) {
        this.publisher_bookDTOPK = new Publisher_BookDTOPK(publisherid,bookid);
    }

    public Publisher_BookDTO(Publisher_BookDTOPK publisher_bookDTOPK, String bookname, String publishername) {
        this.publisher_bookDTOPK = publisher_bookDTOPK;
        this.bookname = bookname;
        this.publishername = publishername;
    }

    public Publisher_BookDTO(String publisherid, String bookid, String bookname, String publishername) {
        this.publisher_bookDTOPK = new Publisher_BookDTOPK(publisherid,bookid);
        this.bookname = bookname;
        this.publishername = publishername;
    }

    public Publisher_BookDTOPK getPublisher_bookDTOPK() {
        return publisher_bookDTOPK;
    }

    public void setPublisher_bookDTOPK(Publisher_BookDTOPK publisher_bookDTOPK) {
        this.publisher_bookDTOPK = publisher_bookDTOPK;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getPublishername() {
        return publishername;
    }

    public void setPublishername(String publishername) {
        this.publishername = publishername;
    }
}
