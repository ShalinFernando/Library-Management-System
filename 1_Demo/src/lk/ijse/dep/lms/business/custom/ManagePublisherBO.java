package lk.ijse.dep.lms.business.custom;

import lk.ijse.dep.lms.business.SuperBO;
import lk.ijse.dep.lms.dto.PublisherDTO;
import lk.ijse.dep.lms.dto.Publisher_BookDTOPK;
import lk.ijse.dep.lms.entity.Publisher;
import lk.ijse.dep.lms.entity.Publisher_Book;

import java.util.List;

public interface ManagePublisherBO extends SuperBO {
    List<PublisherDTO> getPublisher() throws Exception;

    boolean createPublisher(PublisherDTO dto) throws Exception;

    boolean updatePublisher(PublisherDTO dto) throws Exception;

    boolean deletePublisher(String id) throws Exception;

    PublisherDTO findPublisher(String id) throws Exception;
}
