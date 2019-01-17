package lk.ijse.dep.lms.business.custom;

import lk.ijse.dep.lms.business.SuperBO;
import lk.ijse.dep.lms.dto.BorrowDTOPK;
import lk.ijse.dep.lms.dto.MemberDTO;
import lk.ijse.dep.lms.entity.Borrow;
import lk.ijse.dep.lms.entity.Member;

import java.util.List;

public interface ManageMemberBO extends SuperBO {
    List<MemberDTO> getMember() throws Exception;

    boolean createMember(MemberDTO dto) throws Exception;

    boolean updateMember(MemberDTO dto) throws Exception;

    boolean deleteMember(String id) throws Exception;

    MemberDTO findMember(String id) throws Exception;
}
