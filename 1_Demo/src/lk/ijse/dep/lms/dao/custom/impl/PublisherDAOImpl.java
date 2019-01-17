package lk.ijse.dep.lms.dao.custom.impl;

import lk.ijse.dep.lms.dao.CrudDAO;
import lk.ijse.dep.lms.dao.CrudUtil;
import lk.ijse.dep.lms.dao.custom.PublisherDAO;
import lk.ijse.dep.lms.entity.Publisher;
import lk.ijse.dep.lms.entity.SuperEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PublisherDAOImpl implements PublisherDAO {

    @Override
    public Optional<Publisher> find(String key) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Publisher WHERE publisherid = ?", key);
        Publisher p = null;
        if (rst.next()){
            p = new Publisher(rst.getString(1),rst.getString(2),rst.getString(3));
        }
        return Optional.ofNullable(p);
    }

    @Override
    public Optional<List<Publisher>> findAll() throws Exception {
        List<Publisher> allPublishers = new ArrayList<>();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Publisher");
        while (rst.next()){
            Publisher publisher = new Publisher(rst.getString(1),rst.getString(2),rst.getString(3));
            allPublishers.add(publisher);
        }
        return Optional.ofNullable(allPublishers);
    }

    @Override
    public boolean save(Publisher entity) throws Exception {
        return CrudUtil.<Integer>execute("INSERT INTO Publisher VALUES (?,?,?)",
                entity.getPublisherid(),entity.getPublishername(),entity.getPublisheremail())>0;
    }

    @Override
    public boolean update(Publisher entity) throws Exception {
        return CrudUtil.<Integer>execute("UPDATE Publisher SET publishername = ? , publisheremail = ? WHERE publisherid = ?",
                entity.getPublishername(),entity.getPublisheremail(),entity.getPublisherid())>0;
    }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.<Integer>execute("DELETE FROM Publisher WHERE publisherid = ? ", key)>0;
    }
}
