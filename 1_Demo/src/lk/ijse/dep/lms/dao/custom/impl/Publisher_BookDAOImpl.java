package lk.ijse.dep.lms.dao.custom.impl;

import lk.ijse.dep.lms.dao.CrudDAO;
import lk.ijse.dep.lms.dao.CrudUtil;
import lk.ijse.dep.lms.dao.custom.Publisher_BookDAO;
import lk.ijse.dep.lms.entity.Publisher_Book;
import lk.ijse.dep.lms.entity.Publisher_BookPK;
import lk.ijse.dep.lms.entity.SuperEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Publisher_BookDAOImpl implements Publisher_BookDAO {

    @Override
    public Optional<Publisher_Book> find(Publisher_BookPK key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Publisher_Book WHERE publisherid = ? AND bookid = ?",key.getPublisherid(),key.getBookid());
        Publisher_Book p = null;
        if (rst.next()){
            p = new Publisher_Book(rst.getString(1),rst.getString(2));
        }
        return Optional.ofNullable(p);
    }

    @Override
    public Optional<List<Publisher_Book>> findAll() throws Exception {
        List<Publisher_Book> allPublisher_Book = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Publisher_Book");
        while (rst.next()){
            Publisher_Book publisher_book = new Publisher_Book(rst.getString(1),rst.getString(2));
            allPublisher_Book.add(publisher_book);
        }
        return Optional.ofNullable(allPublisher_Book);
    }

    @Override
    public boolean save(Publisher_Book entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Publisher_Book VALUES (?,?)",
                entity.getPublisher_bookPK().getPublisherid(),entity.getPublisher_bookPK().getBookid())>0;
    }

    @Override
    public boolean update(Publisher_Book entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Publisher_BookPK key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Publisher_Book WHERE publisher = ? AND bookid = ?", key.getPublisherid(),key.getBookid())>0;
    }
}
