package lk.ijse.dep.lms.business.custom.impl;

import lk.ijse.dep.lms.business.Converter;
import lk.ijse.dep.lms.business.custom.ManageReturnsBO;
import lk.ijse.dep.lms.dao.DAOFactory;
import lk.ijse.dep.lms.dao.custom.ReturnsDAO;
import lk.ijse.dep.lms.dto.ReturnsDTO;
import lk.ijse.dep.lms.dto.ReturnsDTOPK;
import lk.ijse.dep.lms.entity.Returns;
import lk.ijse.dep.lms.entity.ReturnsPK;

import java.util.List;

public class ManageReturnsBOImpl implements ManageReturnsBO {
    private ReturnsDAO returnsDAO;
    public ManageReturnsBOImpl(){
        returnsDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RETURNS);
    }
    @Override
    public List<ReturnsDTO> getReturns() throws Exception {
        return returnsDAO.findAll().map(Converter::<ReturnsDTO>getDTOList).get();
    }

    @Override
    public boolean createReturns(ReturnsDTO dto) throws Exception {
        return returnsDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateReturns(ReturnsDTO dto) throws Exception {
        return returnsDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteReturns(ReturnsDTOPK id) throws Exception {
        return returnsDAO.delete(new ReturnsPK(id.getMemberid(),id.getBookid()));
    }

    @Override
    public ReturnsDTO findReturns(ReturnsDTOPK id) throws Exception {
        return returnsDAO.find(new ReturnsPK(id.getMemberid(),id.getBookid())).map(Converter::<ReturnsDTO>getDTO).orElse(null);
    }
}
