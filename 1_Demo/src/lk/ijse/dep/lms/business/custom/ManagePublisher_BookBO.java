package lk.ijse.dep.lms.business.custom;

import lk.ijse.dep.lms.business.SuperBO;
import lk.ijse.dep.lms.dto.Publisher_BookDTO;
import lk.ijse.dep.lms.dto.Publisher_BookDTOPK;
import lk.ijse.dep.lms.entity.Member;
import lk.ijse.dep.lms.entity.Publisher_Book;

import java.util.List;

public interface ManagePublisher_BookBO extends SuperBO {
    List<Publisher_BookDTO> getPublisher_Book() throws Exception;

    boolean createPublisher_Book(Publisher_BookDTO dto) throws Exception;

    boolean updatePublisher_Book(Publisher_BookDTO dto) throws Exception;

    boolean deletePublisher_Book(Publisher_BookDTOPK id) throws Exception;

    Publisher_BookDTO findPublisher_Book(Publisher_BookDTOPK id) throws Exception;
}
