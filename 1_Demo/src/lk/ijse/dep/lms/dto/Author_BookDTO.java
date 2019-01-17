package lk.ijse.dep.lms.dto;

public class Author_BookDTO extends SuperDTO {
    private Author_BookDTOPK author_bookDTOPK;

    public Author_BookDTO() {
    }


    public Author_BookDTO(String authorid , String bookid) {
        this.author_bookDTOPK = new Author_BookDTOPK(authorid,bookid);
    }

    public Author_BookDTO(Author_BookDTOPK author_bookDTOPK) {
        this.author_bookDTOPK = author_bookDTOPK;
    }


    public Author_BookDTOPK getAuthor_bookDTOPK() {
        return author_bookDTOPK;
    }

    public void setAuthor_bookDTOPK(Author_BookDTOPK author_bookDTOPK) {
        this.author_bookDTOPK = author_bookDTOPK;
    }

}
