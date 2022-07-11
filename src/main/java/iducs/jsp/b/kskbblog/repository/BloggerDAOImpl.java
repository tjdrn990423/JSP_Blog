package iducs.jsp.b.kskbblog.repository;

import iducs.jsp.b.kskbblog.model.Blog;
import iducs.jsp.b.kskbblog.model.Blogger;
import iducs.jsp.b.kskbblog.util.Pagination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BloggerDAOImpl extends DAOlmplOracle implements BloggerDAO {

    Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public BloggerDAOImpl() {
        conn = getConnection();
    }
    @Override
    public int create(Blogger blogger) {
        String query = "insert into blogger201812045 values(seq_blogger201812045.nextVal, ?, ?, ?, ?,?)";
        int rows = 0; //질의 처리 결과 영향받은 행의 수
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, blogger.getEmail());
            pstmt.setString(2, blogger.getPw());
            pstmt.setString(3, blogger.getName());
            pstmt.setString(4, blogger.getPhone());
            pstmt.setString(5, blogger.getAddress());
            rows = pstmt.executeUpdate();//1이상이면 정상 ,0이하면 오류
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public Blogger read(Blogger blogger) {

        Blogger retBlogger = null;
        // 지난주 email 조건 -> id 조건으로 조회
        String sql = "select * from blogger201812045 where  email=? and pw=?"; // 유일키(unique key)로 조회
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blogger.getEmail());
            pstmt.setString(2, blogger.getPw());
            rs = pstmt.executeQuery();
            if(rs.next()) { // rs.next()는 반환된 객체에 속한 요소가 있는지를 반환하고, 다름 요소로 접근
                retBlogger = new Blogger();
                retBlogger.setId(rs.getLong("id"));
                retBlogger.setEmail(rs.getString("email"));
                retBlogger.setPw(rs.getString("pw"));
                retBlogger.setName(rs.getString("name"));
                retBlogger.setPhone(rs.getString("phone"));
                retBlogger.setAddress(rs.getString("address"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retBlogger;
    }

    @Override
    public Blogger readBlogger(Blogger blogger) {

        Blogger retBlogger = null;
        // 지난주 email 조건 -> id 조건으로 조회
        String sql = "select * from blogger201812045 where id=?"; // 유일키(unique key)로 조회
        try {
            pstmt = conn.prepareStatement(sql);
            String bloggerId = Long.toString(blogger.getId());
            pstmt.setString(1, bloggerId);
            rs = pstmt.executeQuery();
            if(rs.next()) { // rs.next()는 반환된 객체에 속한 요소가 있는지를 반환하고, 다름 요소로 접근
                retBlogger = new Blogger();
                retBlogger.setId(rs.getLong("id"));
                retBlogger.setEmail(rs.getString("email"));
                retBlogger.setPw(rs.getString("pw"));
                retBlogger.setName(rs.getString("name"));
                retBlogger.setPhone(rs.getString("phone"));
                retBlogger.setAddress(rs.getString("address"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return retBlogger;
    }

    @Override
    public List<Blogger> readList() {
        ArrayList<Blogger> bloggerList = null;
        String sql = "select * from blogger201812045 where email not in 'admin@induk.ac.kr'";
        try {
            stmt = conn.createStatement();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만
            // executeUpdate()는 insert, update, delete에 사용 가능
            if((rs = stmt.executeQuery(sql)) != null) { // 질의 결과가 ResultSet 형태로 반환
                bloggerList = new ArrayList<Blogger>();
                while (rs.next()) {
                    Blogger blogger = new Blogger();
                    blogger = setMemberRs(rs);
                    bloggerList.add(blogger);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bloggerList;
    }
    private Blogger setMemberRs(ResultSet rs) throws SQLException {
        // rs : record 집합, re.getString(1) : 현재 레코드의 첫번째 필드 값
        Blogger retBlogger = new Blogger();
        retBlogger.setId(rs.getLong(1));
        retBlogger.setEmail(rs.getString(2));
        retBlogger.setPw(rs.getString(3));
        retBlogger.setName(rs.getString(4));
        retBlogger.setPhone(rs.getString(5));
        retBlogger.setAddress(rs.getString(6));
        return retBlogger;
    }
    @Override
    public int readTotalRows() {
        int rows = 0;
        String sql = "select count(*) as totalRows from blogger201812045";
        try {
            stmt = conn.createStatement();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                rows = rs.getInt("totalRows");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public List<Blogger> readListPagination(Pagination pagination) {
        ArrayList<Blogger> bloggerList = null;
        String sql = "select * from (" +
                "select A.*, rownum as rnum from (" +
                "select * from blogger201812045 where email not in 'admin@induk.ac.kr') A) where rnum >= ? and rnum <= ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pagination.getFirstRow());
            pstmt.setInt(2, pagination.getEndRow());
            rs = pstmt.executeQuery();
            // execute(sql)는 모든 질의에 사용가능, executeQuery(sql)는 select에만, executeUpdate()는 insert, update, delete에 사용 가능
            bloggerList = new ArrayList<Blogger>();
            while (rs.next()) {
                Blogger blogger = new Blogger();
                blogger.setId(rs.getLong("id")); // id 값도 dto에 저장
                blogger.setEmail(rs.getString("email"));
                blogger.setPw(rs.getString("pw"));
                blogger.setName(rs.getString("name"));
                blogger.setPhone(rs.getString("phone"));
                blogger.setAddress(rs.getString("address"));
                bloggerList.add(blogger);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bloggerList;
    }

    @Override
    public int update(Blogger blogger) {
        String sql = "update blogger201812045 set name=?, email=?, address=?, phone=? where id=?";
        int rows = 0; //질의 처리 결과 영향받은 행의 수
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, blogger.getName());
            pstmt.setString(2, blogger.getEmail());
            pstmt.setString(3, blogger.getAddress());
            pstmt.setString(4, blogger.getPhone());
            pstmt.setLong(5, blogger.getId());
            rows = pstmt.executeUpdate();//1이상이면 정상 ,0이하면 오류
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    @Override
    public int delete(Blogger blogger) {
        String sql = "delete from blogger201812045 where id=?";
        int rows = 0; //질의 처리 결과 영향받은 행의 수
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, blogger.getId());
            rows = pstmt.executeUpdate();//1이상이면 정상 ,0이하면 오류
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

}
