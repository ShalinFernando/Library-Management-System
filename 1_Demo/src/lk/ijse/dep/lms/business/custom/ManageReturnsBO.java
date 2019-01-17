package lk.ijse.dep.lms.business.custom;

import lk.ijse.dep.lms.business.SuperBO;
import lk.ijse.dep.lms.dto.ReturnsDTO;
import lk.ijse.dep.lms.dto.ReturnsDTOPK;
import lk.ijse.dep.lms.entity.Publisher;
import lk.ijse.dep.lms.entity.Returns;

import java.util.List;

public interface ManageReturnsBO extends SuperBO {
    List<ReturnsDTO> getReturns() throws Exception;

    boolean createReturns(ReturnsDTO dto) throws Exception;

    boolean updateReturns(ReturnsDTO dto) throws Exception;

    boolean deleteReturns(ReturnsDTOPK id) throws Exception;

    ReturnsDTO findReturns(ReturnsDTOPK id) throws Exception;
}
