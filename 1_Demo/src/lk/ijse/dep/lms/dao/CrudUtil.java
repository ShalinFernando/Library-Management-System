package lk.ijse.dep.lms.dao;


import lk.ijse.dep.lms.dbconnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudUtil {

    private CrudUtil(){}

    public static <T> T execute(String sql, Object... params) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        int parametersCount = getParametersCount(sql);

        if (params.length != parametersCount) {
            throw new RuntimeException("Parameters count mismatched error");
        }

        for (int i = 0; i < parametersCount; i++) {
            pstm.setObject(i + 1, params[i]);
        }

        return sql.trim().startsWith("SELECT") ? (T) pstm.executeQuery() : (T) (Integer) pstm.executeUpdate();
    }

    private static int getParametersCount(String sql) {
        return sql.concat(" ").split("[?]").length - 1;
    }

}
