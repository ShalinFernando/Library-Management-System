package lk.ijse.dep.lms.business.custom;

import lk.ijse.dep.lms.business.SuperBO;
import lk.ijse.dep.lms.dto.BooksDTO;
import lk.ijse.dep.lms.entity.Author;
import lk.ijse.dep.lms.entity.Books;

import java.util.List;

public interface ManageBooksBO extends SuperBO {
    List<BooksDTO> getBooks() throws Exception;

    boolean createBooks(BooksDTO dto) throws Exception;

    boolean updateBooks(BooksDTO dto) throws Exception;

    boolean deleteBooks(String id) throws Exception;

    BooksDTO findBooks(String id) throws Exception;
}
