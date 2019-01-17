package lk.ijse.dep.lms.dao.custom.impl;

import lk.ijse.dep.lms.dao.CrudDAO;
import lk.ijse.dep.lms.dao.CrudUtil;
import lk.ijse.dep.lms.dao.custom.AuthorDAO;
import lk.ijse.dep.lms.entity.Author;
import lk.ijse.dep.lms.entity.SuperEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthorDAOImpl implements AuthorDAO {

    @Override
    public Optional<Author> find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Author WHERE authorid = ? ",key);
        Author a = null;
        if (rst.next()){
            a = new Author(rst.getString("authorid"),
            rst.getString("authorname"),
            rst.getString("authoremail"));
        }
        return Optional.ofNullable(a);
    }

    @Override
    public Optional<List<Author>> findAll() throws Exception {
        List<Author> allAuthors = new ArrayList<>();
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Author");
        while (resultSet.next()){
            String authorid = resultSet.getString(1);
            String authorname = resultSet.getString(2);
            String authoremail = resultSet.getString(3);

            Author author = new Author(authorid,authorname,authoremail);
            allAuthors.add(author);
        }
        return Optional.ofNullable(allAuthors);
    }

    @Override
    public boolean save(Author entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Author VALUES (?,?,?)",
                entity.getAuthorid(),entity.getAuthorname(),entity.getAuthoremail())>0;
    }

    @Override
    public boolean update(Author entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Author SET authorname = ? , authoremail = ? WHERE authorid = ?",
                entity.getAuthorname(),entity.getAuthoremail(),entity.getAuthorid())>0;
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Author WHERE authorid = ? ", key)>0;
    }
}
