package iducs.jsp.b.kskbblog.repository;
import java.sql.*;
public class DAOlmplOracle implements DAO{
    private Connection conn = null;
    @Override
    public Connection getConnection() {
        /*
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String dbUser = "system";
        String dbPw = "1234";
         */

        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String dbUser = "system";
        String dbPw = "1234";
        try {
            Class.forName("oracle.jdbc.OracleDriver"); //드라이버 로딩
            // 적재된 드라이버 관리자 객체의 getConnection() 정적메소드를 호출하여 Connection 객체 생성
            // Connection -> Statement / PreparedStatement 객체 생성 -> query 실행
            // -> Resultset (read) or 영향받은 row 수(create, update, delete)반환
            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPw);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }

    @Override
    public void closeResources(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {

    }

}
