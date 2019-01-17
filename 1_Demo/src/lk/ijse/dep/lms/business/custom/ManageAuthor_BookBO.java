package lk.ijse.dep.lms.business.custom;

import lk.ijse.dep.lms.business.SuperBO;
import lk.ijse.dep.lms.dto.Author_BookDTO;
import lk.ijse.dep.lms.dto.Author_BookDTOPK;

import java.util.List;

public interface ManageAuthor_BookBO extends SuperBO {
    List<Author_BookDTO> getAuthor_Books() throws Exception;

    boolean createAuthor_Books(Author_BookDTO dto) throws Exception;

    boolean updateAuthor_Books(Author_BookDTO dto) throws Exception;

    boolean deleteAuthor_Books(Author_BookDTOPK authorBookDTOPK) throws Exception;

    Author_BookDTO findAuthor_Book(Author_BookDTOPK id) throws Exception;
}
