package lk.ijse.dep.lms.business.custom.impl;

import lk.ijse.dep.lms.business.Converter;
import lk.ijse.dep.lms.business.custom.ManageBooksBO;
import lk.ijse.dep.lms.dao.DAOFactory;
import lk.ijse.dep.lms.dao.custom.BooksDAO;
import lk.ijse.dep.lms.dto.BooksDTO;
import lk.ijse.dep.lms.entity.Books;

import java.util.List;

public class ManageBooksBOImpl implements ManageBooksBO {
    private BooksDAO booksDAO;
    public ManageBooksBOImpl(){
        booksDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BOOKS);
    }
    @Override
    public List<BooksDTO> getBooks() throws Exception {
        return booksDAO.findAll().map(Converter::<BooksDTO>getDTOList).get();
    }

    @Override
    public boolean createBooks(BooksDTO dto) throws Exception {
        return booksDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateBooks(BooksDTO dto) throws Exception {
        return booksDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteBooks(String id) throws Exception {
        return booksDAO.delete(id);
    }

    @Override
    public BooksDTO findBooks(String id) throws Exception {
        return booksDAO.find(id).map(Converter::<BooksDTO>getDTO).orElse(null);
    }
}
