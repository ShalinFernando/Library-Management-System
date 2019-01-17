package lk.ijse.dep.lms.dao.custom;

import lk.ijse.dep.lms.dao.CrudDAO;
import lk.ijse.dep.lms.entity.Borrow;
import lk.ijse.dep.lms.entity.BorrowPK;

public interface BorrowDAO extends CrudDAO<Borrow, String> {
    int count() throws Exception;
}
