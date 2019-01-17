package lk.ijse.dep.lms.dao.custom;

import lk.ijse.dep.lms.dao.SuperDAO;
import lk.ijse.dep.lms.entity.CustomEntity;

import java.util.List;
import java.util.Optional;

public interface QueryDAO extends SuperDAO {
    Optional<List<CustomEntity>> findbookauthorpublisherdetails(String bookid) throws Exception;

}
