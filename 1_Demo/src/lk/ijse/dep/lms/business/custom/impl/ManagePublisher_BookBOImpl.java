package lk.ijse.dep.lms.business.custom.impl;

import lk.ijse.dep.lms.business.Converter;
import lk.ijse.dep.lms.business.custom.ManagePublisher_BookBO;
import lk.ijse.dep.lms.dao.DAOFactory;
import lk.ijse.dep.lms.dao.custom.Publisher_BookDAO;
import lk.ijse.dep.lms.dto.Publisher_BookDTO;
import lk.ijse.dep.lms.dto.Publisher_BookDTOPK;
import lk.ijse.dep.lms.entity.Publisher_Book;
import lk.ijse.dep.lms.entity.Publisher_BookPK;

import java.util.List;

public class ManagePublisher_BookBOImpl implements ManagePublisher_BookBO {
    private Publisher_BookDAO publisher_bookDAO;
    public ManagePublisher_BookBOImpl(){
        publisher_bookDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PUBLISHER_BOOK);
    }
    @Override
    public List<Publisher_BookDTO> getPublisher_Book() throws Exception {
        return publisher_bookDAO.findAll().map(Converter::<Publisher_BookDTO>getDTOList).get();
    }

    @Override
    public boolean createPublisher_Book(Publisher_BookDTO dto) throws Exception {
        return publisher_bookDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updatePublisher_Book(Publisher_BookDTO dto) throws Exception {
        return publisher_bookDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deletePublisher_Book(Publisher_BookDTOPK id) throws Exception {
        return publisher_bookDAO.delete(new Publisher_BookPK(id.getPublisherid(),id.getBookid()));
    }

    @Override
    public Publisher_BookDTO findPublisher_Book(Publisher_BookDTOPK id) throws Exception {
        return publisher_bookDAO.find(new Publisher_BookPK(id.getPublisherid(),id.getBookid())).map(Converter::<Publisher_BookDTO>getDTO).orElse(null);
    }
}
