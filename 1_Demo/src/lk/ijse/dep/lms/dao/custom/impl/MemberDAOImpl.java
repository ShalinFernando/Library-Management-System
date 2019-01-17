package lk.ijse.dep.lms.dao.custom.impl;

import lk.ijse.dep.lms.dao.CrudDAO;
import lk.ijse.dep.lms.dao.CrudUtil;
import lk.ijse.dep.lms.dao.custom.MemberDAO;
import lk.ijse.dep.lms.entity.Member;
import lk.ijse.dep.lms.entity.SuperEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public Optional<Member> find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Member WHERE memberid = ?" ,key);
        Member m = null;
        if (rst.next()){
            m = new Member(rst.getString(1),rst.getString(2),rst.getString(3));
        }
        return Optional.ofNullable(m);
    }

    @Override
    public Optional<List<Member>> findAll() throws Exception {
        List<Member> allMembers = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Member");
        while (rst.next()){
            Member member = new Member(rst.getString(1),rst.getString(2),rst.getString(3));
            allMembers.add(member);
        }
        return Optional.ofNullable(allMembers);
    }

    @Override
    public boolean save(Member entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Member VALUES (?,?,?)",entity.getMemberid(),entity.getMembername(),entity.getEmail())>0;
    }

    @Override
    public boolean update(Member entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Member SET membername = ? , email = ? WHERE memberid = ?", entity.getMembername(),entity.getEmail(),entity.getMemberid())>0;
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Member WHERE memberid = ?", key)>0;
    }
}
