package lk.ijse.dep.lms.dao.custom.impl;

import lk.ijse.dep.lms.dao.CrudDAO;
import lk.ijse.dep.lms.dao.CrudUtil;
import lk.ijse.dep.lms.dao.custom.ReturnsDAO;
import lk.ijse.dep.lms.entity.Returns;
import lk.ijse.dep.lms.entity.ReturnsPK;
import lk.ijse.dep.lms.entity.SuperEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReturnsDAOImpl implements ReturnsDAO {


    @Override
    public Optional<Returns> find(ReturnsPK key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Returns WHERE borrowid = ?",key.getMemberid(),key.getBookid());
        Returns returns = null;
        if (rst.next()){
            returns = new Returns(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDate(4).toLocalDate(),rst.getDate(5).toLocalDate(),rst.getInt(6));

        }
        return Optional.ofNullable(returns);
    }

    @Override
    public Optional<List<Returns>> findAll() throws Exception {
        List<Returns> allReturns = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Publisher");
        while (rst.next()){
            Returns r = new Returns(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDate(4).toLocalDate(),rst.getDate(5).toLocalDate(),rst.getInt(6));
            allReturns.add(r);
        }
        return Optional.ofNullable(allReturns);
    }

    @Override
    public boolean save(Returns entity) throws Exception {
        return CrudUtil.<Integer>execute("INSRET INTO Returns VALUES (?,?,?,?,?,?)",
                entity.getBorrowid(),entity.getMemberid(),entity.getBookid(),entity.getReturndate(),entity.getDuedate(),entity.getFine())>0;
    }

    @Override
    public boolean update(Returns entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Returns SET memberid = ? , bookid = ? , duedate = ? , returndate = ? , fine = ? WHERE borrowid = ?",
                entity.getDuedate(),entity.getReturndate(),entity.getFine(),entity.getMemberid(),entity.getBookid(),entity.getBorrowid())>0;
    }

    @Override
    public boolean delete(ReturnsPK key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Returns WHERE borrowid = ?",
                key.getMemberid(),key.getBookid())>0;
    }
}
