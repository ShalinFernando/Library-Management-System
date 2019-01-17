package lk.ijse.dep.lms.business.custom;

import lk.ijse.dep.lms.business.SuperBO;
import lk.ijse.dep.lms.dto.AuthorDTO;
import lk.ijse.dep.lms.dto.Author_BookDTO;
import lk.ijse.dep.lms.dto.Author_BookDTOPK;
import lk.ijse.dep.lms.entity.Author;

import java.util.List;

public interface ManageAuthorBO extends SuperBO {
    List<AuthorDTO> getAuthor() throws Exception;

    boolean createAuthor(AuthorDTO dto) throws Exception;

    boolean updateAuthor(AuthorDTO dto) throws Exception;

    boolean deleteAuthor(String id) throws Exception;

    AuthorDTO findAuthor(String id) throws Exception;
}
