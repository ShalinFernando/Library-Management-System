package lk.ijse.dep.lms.business.custom.impl;

import lk.ijse.dep.lms.business.Converter;
import lk.ijse.dep.lms.business.custom.ManageAuthor_BookBO;
import lk.ijse.dep.lms.dao.DAOFactory;
import lk.ijse.dep.lms.dao.custom.Author_BookDAO;
import lk.ijse.dep.lms.dto.Author_BookDTO;
import lk.ijse.dep.lms.dto.Author_BookDTOPK;
import lk.ijse.dep.lms.entity.Author_BookPK;

import java.util.List;

public class ManageAuthor_BookBOImpl implements ManageAuthor_BookBO {



    private Author_BookDAO author_bookDAOl;

    public ManageAuthor_BookBOImpl(){
        author_bookDAOl = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.AUTHOR_BOOK);
    }

    @Override
    public List<Author_BookDTO> getAuthor_Books() throws Exception {
        return author_bookDAOl.findAll().map(Converter:: <Author_BookDTO>getDTOList).get();
    }

    @Override
    public boolean createAuthor_Books(Author_BookDTO dto) throws Exception {
        return author_bookDAOl.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateAuthor_Books(Author_BookDTO dto) throws Exception {
        return author_bookDAOl.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteAuthor_Books(Author_BookDTOPK authorBookDTOPK) throws Exception {
        return author_bookDAOl.delete(new Author_BookPK(authorBookDTOPK.getAuthorid(),authorBookDTOPK.getBookid()));
    }

    @Override
    public Author_BookDTO findAuthor_Book(Author_BookDTOPK id) throws Exception {
        return author_bookDAOl.find(new Author_BookPK(id.getAuthorid(),id.getBookid())).map(Converter::<Author_BookDTO>getDTO).orElse(null);
    }
}
