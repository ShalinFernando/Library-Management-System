package lk.ijse.dep.lms.business.custom;

import lk.ijse.dep.lms.business.SuperBO;
import lk.ijse.dep.lms.dto.BooksDTO2;
import lk.ijse.dep.lms.dto.BorrowDTO;
import lk.ijse.dep.lms.dto.BorrowDTOPK;

import java.util.List;

public interface ManageBorrowBO extends SuperBO {
    List<BorrowDTO> getBorrow() throws Exception;

    boolean createBorrow(BorrowDTO dto) throws Exception;

    boolean updateBorrow(BorrowDTO dto) throws Exception;

    boolean deleteBorrow(String id) throws Exception;

    BorrowDTO findBorrow(String id) throws Exception;

    List<BooksDTO2> getbookdetailswithauthorandpublisher(String bookid) throws Exception;

    String generateOrderId() throws Exception;



}
