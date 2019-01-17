package lk.ijse.dep.lms.business.custom.impl;

import lk.ijse.dep.lms.business.Converter;
import lk.ijse.dep.lms.business.custom.ManageBorrowBO;
import lk.ijse.dep.lms.dao.DAOFactory;
import lk.ijse.dep.lms.dao.custom.BorrowDAO;
import lk.ijse.dep.lms.dao.custom.QueryDAO;
import lk.ijse.dep.lms.dto.BooksDTO2;
import lk.ijse.dep.lms.dto.BorrowDTO;
import lk.ijse.dep.lms.dto.BorrowDTOPK;
import lk.ijse.dep.lms.entity.BorrowPK;

import java.util.List;

public class ManageBorrowBOImpl implements ManageBorrowBO {
    private BorrowDAO borrowDAO;
    private QueryDAO queryDAO;

    public ManageBorrowBOImpl(){

        borrowDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.BORROW);
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }
    @Override
    public List<BorrowDTO> getBorrow() throws Exception {
        return borrowDAO.findAll().map(Converter::<BorrowDTO>getDTOList).get();
    }

    @Override
    public boolean createBorrow(BorrowDTO dto) throws Exception {
        return borrowDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateBorrow(BorrowDTO dto) throws Exception {
        return borrowDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteBorrow(String id) throws Exception {
        return borrowDAO.delete(id);
    }

    @Override
    public BorrowDTO findBorrow(String id) throws Exception {
        return borrowDAO.find(id).map(Converter::<BorrowDTO>getDTO).orElse(null);
    }

    @Override
    public List<BooksDTO2> getbookdetailswithauthorandpublisher(String bookid) throws Exception {
        return queryDAO.findbookauthorpublisherdetails(bookid).map(customEntities -> {
            return Converter.getDTOList(customEntities, BooksDTO2.class);
        }).get();
    }

    @Override
    public String generateOrderId() throws Exception {
        return borrowDAO.count() + 1 + "";
    }
}
