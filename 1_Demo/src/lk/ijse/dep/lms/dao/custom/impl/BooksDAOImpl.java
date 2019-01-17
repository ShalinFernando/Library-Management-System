package lk.ijse.dep.lms.dao.custom.impl;

import lk.ijse.dep.lms.dao.CrudDAO;
import lk.ijse.dep.lms.dao.CrudUtil;
import lk.ijse.dep.lms.dao.custom.BooksDAO;
import lk.ijse.dep.lms.entity.Books;
import lk.ijse.dep.lms.entity.SuperEntity;
import org.omg.CORBA.INTERNAL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BooksDAOImpl implements BooksDAO {

    @Override
    public Optional<Books> find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Books WHERE bookid = ? ", key);
        Books b = null;
        if (rst.next()){
            b = new Books(rst.getString(1),rst.getString(2),rst.getString(3));
        }
        return Optional.ofNullable(b);
    }

    @Override
    public Optional<List<Books>> findAll() throws Exception {
        List<Books> allBooks = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Books");
        while (rst.next()){
            Books books = new Books(rst.getString(1),rst.getString(2),rst.getString(3));
            allBooks.add(books);
        }
        return Optional.ofNullable(allBooks);
    }

    @Override
    public boolean save(Books entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Books VALUES (?,?,?)",
                entity.getBookid(),entity.getBookname(),entity.getBookcategory())>0;
    }

    @Override
    public boolean update(Books entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Books SET bookname = ? , bookcategory = ? WHERE bookid = ? ",
                entity.getBookname(),entity.getBookcategory(),entity.getBookid())>0;

    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Books WHERE bookid = ?", key)>0;
    }
}
