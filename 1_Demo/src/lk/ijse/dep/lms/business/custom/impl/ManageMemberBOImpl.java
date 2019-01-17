package lk.ijse.dep.lms.business.custom.impl;

import lk.ijse.dep.lms.business.Converter;
import lk.ijse.dep.lms.business.custom.ManageMemberBO;
import lk.ijse.dep.lms.dao.DAOFactory;
import lk.ijse.dep.lms.dao.custom.MemberDAO;
import lk.ijse.dep.lms.dto.MemberDTO;
import lk.ijse.dep.lms.entity.Member;

import java.util.List;

public class ManageMemberBOImpl implements ManageMemberBO {
    private MemberDAO memberDAO;
    public ManageMemberBOImpl(){
        memberDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.MEMBER);
    }

    @Override
    public List<MemberDTO> getMember() throws Exception {
        return memberDAO.findAll().map(Converter::<MemberDTO>getDTOList).get();
    }

    @Override
    public boolean createMember(MemberDTO dto) throws Exception {
        return memberDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateMember(MemberDTO dto) throws Exception {
        return memberDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteMember(String id) throws Exception {
        return memberDAO.delete(id);
    }

    @Override
    public MemberDTO findMember(String id) throws Exception {
        return memberDAO.find(id).map(Converter::<MemberDTO>getDTO).orElse(null);
    }
}
