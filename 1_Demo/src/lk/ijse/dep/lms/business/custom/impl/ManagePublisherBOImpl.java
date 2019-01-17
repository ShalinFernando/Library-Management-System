package lk.ijse.dep.lms.business.custom.impl;

import lk.ijse.dep.lms.business.Converter;
import lk.ijse.dep.lms.business.custom.ManagePublisherBO;
import lk.ijse.dep.lms.dao.DAOFactory;
import lk.ijse.dep.lms.dao.custom.PublisherDAO;
import lk.ijse.dep.lms.dto.PublisherDTO;
import lk.ijse.dep.lms.entity.Publisher;

import java.util.List;

public class ManagePublisherBOImpl implements ManagePublisherBO {
    private PublisherDAO publisherDAO;
    public ManagePublisherBOImpl(){
        publisherDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PUBLISHER);
    }
    @Override
    public List<PublisherDTO> getPublisher() throws Exception {
        return publisherDAO.findAll().map(Converter::<PublisherDTO>getDTOList).get();
    }

    @Override
    public boolean createPublisher(PublisherDTO dto) throws Exception {
        return publisherDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updatePublisher(PublisherDTO dto) throws Exception {
        return publisherDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deletePublisher(String id) throws Exception {
        return publisherDAO.delete(id);
    }

    @Override
    public PublisherDTO findPublisher(String id) throws Exception {
        return publisherDAO.find(id).map(Converter::<PublisherDTO>getDTO).orElse(null);
    }
}
