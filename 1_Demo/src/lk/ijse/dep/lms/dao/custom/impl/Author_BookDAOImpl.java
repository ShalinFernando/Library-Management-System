package lk.ijse.dep.lms.dao.custom.impl;

import lk.ijse.dep.lms.dao.CrudDAO;
import lk.ijse.dep.lms.dao.CrudUtil;
import lk.ijse.dep.lms.dao.custom.Author_BookDAO;
import lk.ijse.dep.lms.entity.Author_Book;
import lk.ijse.dep.lms.entity.Author_BookPK;
import lk.ijse.dep.lms.entity.SuperEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Author_BookDAOImpl implements Author_BookDAO {

    @Override
    public Optional<Author_Book> find(Author_BookPK key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Author_Book WHERE authorid = ? AND bookid = ?", key.getAuthorid(), key.getBookid());
        Author_Book a = null;
        if (rst.next()){
            a = new Author_Book(rst.getString(1),rst.getString(2));
        }
        return Optional.ofNullable(a);
    }

    @Override
    public Optional<List<Author_Book>> findAll() throws Exception {
        List<Author_Book> allAuthor_books = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Author_Book");
        while (rst.next()) {
            String authorid = rst.getString(1);
            String bookid = rst.getString(2);
            Author_Book author_book = new Author_Book(authorid, bookid);
            allAuthor_books.add(author_book);
        }
        return Optional.ofNullable(allAuthor_books);
    }

    @Override
    public boolean save(Author_Book entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Author_Book VALUES (?,?)",
                entity.getAuthor_bookPK().getBookid(),entity.getAuthor_bookPK().getAuthorid())>0;
    }

    @Override
    public boolean update(Author_Book entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Author_BookPK key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Author_Book WHERE authorid = ? AND bookid = ? ",
                key.getAuthorid(),key.getBookid())>0;
    }
}
