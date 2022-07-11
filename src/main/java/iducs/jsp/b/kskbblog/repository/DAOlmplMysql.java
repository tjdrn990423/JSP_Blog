package iducs.jsp.b.kskbblog.repository;
import java.sql.*;

public class DAOlmplMysql implements DAO {
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void closeResources(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {

    }
}
