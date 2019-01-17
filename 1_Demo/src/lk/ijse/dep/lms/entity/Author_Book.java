package lk.ijse.dep.lms.entity;

public class Author_Book extends SuperEntity{
    private Author_BookPK author_bookPK;

    public Author_Book() {
    }

    public Author_Book(String authorid, String bookid){

        this.author_bookPK = new Author_BookPK(authorid,bookid);
    }

    public Author_Book(Author_BookPK author_bookPK) {
        this.author_bookPK = author_bookPK;
    }

    public Author_BookPK getAuthor_bookPK() {
        return author_bookPK;
    }

    public void setAuthor_bookPK(Author_BookPK author_bookPK) {
        this.author_bookPK = author_bookPK;
    }

}
