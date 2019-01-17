package lk.ijse.dep.lms.business.custom.impl;

import lk.ijse.dep.lms.business.Converter;
import lk.ijse.dep.lms.business.custom.ManageAuthorBO;
import lk.ijse.dep.lms.dao.DAOFactory;
import lk.ijse.dep.lms.dao.custom.AuthorDAO;
import lk.ijse.dep.lms.dto.AuthorDTO;
import lk.ijse.dep.lms.entity.Author;

import java.util.List;

public class ManageAuthorBOImpl implements ManageAuthorBO {
    private AuthorDAO authorDAO;
    public ManageAuthorBOImpl(){
        authorDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.AUTHOR);
    }

    @Override
    public List<AuthorDTO> getAuthor() throws Exception {
        return authorDAO.findAll().map(Converter::<AuthorDTO>getDTOList).get();
    }

    @Override
    public boolean createAuthor(AuthorDTO dto) throws Exception {
        return authorDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateAuthor(AuthorDTO dto) throws Exception {
        return authorDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteAuthor(String author) throws Exception {
        return authorDAO.delete(author);
    }

    @Override
    public AuthorDTO findAuthor(String id) throws Exception {
        return authorDAO.find(id).map(Converter::<AuthorDTO>getDTO).orElse(null);
    }
}
