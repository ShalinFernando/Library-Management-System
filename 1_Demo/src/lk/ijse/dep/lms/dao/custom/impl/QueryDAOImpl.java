package lk.ijse.dep.lms.dao.custom.impl;

import lk.ijse.dep.lms.dao.CrudUtil;
import lk.ijse.dep.lms.dao.SuperDAO;
import lk.ijse.dep.lms.dao.custom.QueryDAO;
import lk.ijse.dep.lms.entity.CustomEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public Optional<List<CustomEntity>> findbookauthorpublisherdetails(String bookid) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT b.bookname,a.authorid,p.publisherid FROM Books b\n" +
                "INNER JOIN Publisher_Book p on b.bookid = p.bookid INNER JOIN  Author_Book a on b.bookid = a.bookid\n" +
                "WHERE b.bookid=?;", bookid);
        List<CustomEntity> al = new ArrayList<>();

        while (rst.next()) {
            CustomEntity customEntity = new CustomEntity(rst.getString("bookname"),
                    rst.getString("authorid"),
                    rst.getString("publisherid"));
            al.add(customEntity);

        }
        return Optional.ofNullable(al);
    }
}
