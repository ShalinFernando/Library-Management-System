package lk.ijse.dep.lms.business;


import lk.ijse.dep.lms.dto.*;
import lk.ijse.dep.lms.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static <T extends SuperDTO> T getDTO(SuperEntity entity) {
        if (entity instanceof Author_Book) {
            Author_Book author_book = (Author_Book) entity;
            return (T) new Author_BookDTO(author_book.getAuthor_bookPK().getAuthorid(),author_book.getAuthor_bookPK().getBookid());
        } else if (entity instanceof Author) {
            Author a = (Author) entity;
            return (T) new AuthorDTO(a.getAuthorid(),a.getAuthorname(),a.getAuthoremail());
        }else if (entity instanceof Books){
            Books b = (Books) entity;
            return (T) new BooksDTO(b.getBookid(),b.getBookname(),b.getBookcategory());
        }else if (entity instanceof Borrow){
            Borrow borrow = (Borrow)entity;
            return (T) new BorrowDTO(borrow.getBorrowid(),borrow.getBookid(),borrow.getMemberid(),borrow.getBorrowdate(),borrow.getReturndate());
        }else if (entity instanceof Member){
            Member m = (Member) entity;
            return (T) new MemberDTO(m.getMemberid(),m.getMembername(),m.getEmail());
        }else if (entity instanceof Publisher){
            Publisher p = (Publisher) entity;
            return (T) new PublisherDTO(p.getPublisherid(),p.getPublishername(),p.getPublisheremail());
        }else if (entity instanceof Publisher_Book){
            Publisher_Book pb = (Publisher_Book) entity;
            return (T) new Publisher_BookDTO(pb.getPublisher_bookPK().getPublisherid(),pb.getPublisher_bookPK().getBookid());
        }else if (entity instanceof Returns){
            Returns r = (Returns) entity;
            return (T) new ReturnsDTO(r.getBorrowid(),r.getMemberid(),r.getBookid(),r.getDuedate(),r.getReturndate(),r.getFine());
        }else {
            throw new RuntimeException("This entity can't be converted to a DTO");
        }
    }

    public static <T extends SuperEntity> T getEntity(SuperDTO dto) {
        if (dto instanceof Author_BookDTO) {
            Author_BookDTO author_bookDTO = (Author_BookDTO) dto;
            return (T) new Author_Book(author_bookDTO.getAuthor_bookDTOPK().getAuthorid(),author_bookDTO.getAuthor_bookDTOPK().getBookid());
        } else if (dto instanceof AuthorDTO) {
            AuthorDTO a = (AuthorDTO) dto;
            return (T) new Author(a.getAuthorid(),a.getAuthorname(),a.getAuthoremail());
        }else if (dto instanceof  BooksDTO){
            BooksDTO b = (BooksDTO) dto;
            return (T) new Books(b.getBookid(),b.getBookname(),b.getBookcategory());
        }else if (dto instanceof BorrowDTO){
            BorrowDTO b = (BorrowDTO) dto;
            return (T) new Borrow(b.getBorrowid(),b.getBookid(),b.getMemberid(),b.getBorrowdate(),b.getReturndate());
        }else if (dto instanceof MemberDTO){
            MemberDTO m = (MemberDTO) dto;
            return (T) new Member(m.getMemberid(),m.getMembername(),m.getEmail());
        }else if (dto instanceof PublisherDTO){
            PublisherDTO p = (PublisherDTO) dto;
            return (T) new Publisher(p.getPublisherid(),p.getPublishername(),p.getPublisheremail());
        }else if (dto instanceof Publisher_BookDTO){
            Publisher_BookDTO p = (Publisher_BookDTO) dto;
            return (T) new Publisher_Book(p.getPublisher_bookDTOPK().getPublisherid(),p.getPublisher_bookDTOPK().getBookid());
        }else if (dto instanceof ReturnsDTO){
            ReturnsDTO r = (ReturnsDTO) dto;
            return (T) new Returns(r.getBorrowid(),r.getMemberid(),r.getBookid(),r.getDuedate(),r.getReturndate(),r.getFine());
        }else {
            throw new RuntimeException("This DTO can't be converted to an entity");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<? extends SuperEntity> entities) {
        return entities.stream().map(Converter::<T>getDTO).collect(Collectors.toList());
    }

    public static <T extends SuperEntity> List<T> getEntityList(List<? extends SuperDTO> dtos) {
        return dtos.stream().map(Converter::<T>getEntity).collect(Collectors.toList());

    }

    // =========================================================================

    public static <T extends SuperDTO> T getDTO(CustomEntity entity, Class<T> dtoClass) {
        if (dtoClass == BooksDTO2.class) {
            return (T) new BooksDTO2(entity.getBookid(),entity.getBookname(),entity.getAuthorid(),entity.getPublisherid());
        }
         else {
            throw new RuntimeException("Not Supported DTO");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<CustomEntity> list, Class<T> dtoClass) {
        return list.stream().map(c -> getDTO(c, dtoClass)).collect(Collectors.toList());
    }

}
