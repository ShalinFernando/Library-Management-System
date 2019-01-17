package lk.ijse.dep.lms.entity;

public class Publisher_Book extends SuperEntity{
    private Publisher_BookPK publisher_bookPK;

    public Publisher_Book() {
    }

    public Publisher_Book(Publisher_BookPK publisher_bookPK) {
        this.publisher_bookPK = publisher_bookPK;
    }

    public Publisher_Book(String publisherid, String bookid){
        this.publisher_bookPK = new Publisher_BookPK(publisherid,bookid);
    }


    public Publisher_BookPK getPublisher_bookPK() {
        return publisher_bookPK;
    }

    public void setPublisher_bookPK(Publisher_BookPK publisher_bookPK) {
        this.publisher_bookPK = publisher_bookPK;
    }
}
