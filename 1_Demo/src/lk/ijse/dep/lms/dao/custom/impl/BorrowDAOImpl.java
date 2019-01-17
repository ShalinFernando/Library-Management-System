package lk.ijse.dep.lms.dao.custom.impl;

import lk.ijse.dep.lms.dao.CrudDAO;
import lk.ijse.dep.lms.dao.CrudUtil;
import lk.ijse.dep.lms.dao.custom.BorrowDAO;
import lk.ijse.dep.lms.entity.Borrow;
import lk.ijse.dep.lms.entity.BorrowPK;
import lk.ijse.dep.lms.entity.SuperEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BorrowDAOImpl implements BorrowDAO {

    @Override
    public Optional<Borrow> find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Borrow WHERE borrowid = ?", key);
        Borrow b = null;
        if (rst.next()){
            b = new Borrow(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDate(4).toLocalDate(),rst.getDate(5).toLocalDate());

        }
        return Optional.ofNullable(b);
    }

    @Override
    public Optional<List<Borrow>> findAll() throws Exception {
        List<Borrow> allBorrows = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Borrow");
        while (rst.next()){
            Borrow borrow = new Borrow(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDate(4).toLocalDate(),rst.getDate(5).toLocalDate());
            allBorrows.add(borrow);
        }
        return Optional.ofNullable(allBorrows);
    }

    @Override
    public boolean save(Borrow entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Borrow VALUES (?,?,?,?,?)",
                entity.getBorrowid(),entity.getBookid(),entity.getMemberid(),entity.getBorrowdate(),entity.getReturndate())>0;
    }

    @Override
    public boolean update(Borrow entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Borrow SET borrowdate = ? , returndate = ? , bookid = ? , memberid = ? WHERE borrowid = ? ",
                entity.getBorrowid(),entity.getMemberid(),entity.getBookid(),entity.getBorrowdate(),entity.getReturndate())>0;
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE Borrow WHERE borrowid = ? ", key)>0;
    }

    @Override
    public int count() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT COUNT(*) FROM Borrow");
        if (rst.next()) {
            return rst.getInt(1);
        }
        return 0;
    }
}
