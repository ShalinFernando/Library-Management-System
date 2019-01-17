import lk.ijse.dep.lms.controller.ManageBooksController;
import lk.ijse.dep.lms.dbconnection.DBConnection;
import lk.ijse.dep.lms.dto.BooksDTO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class a   {

    public static void main(String[] args) throws IOException, SQLException {
        Connection connection = DBConnection.getConnection();
        System.out.println(connection);

        BooksDTO[] getbookdetails = ManageBooksController.getbookdetails();
        System.out.println(getbookdetails);

    }
}
