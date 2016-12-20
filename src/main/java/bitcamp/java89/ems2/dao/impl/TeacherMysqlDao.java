package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bitcamp.java89.ems2.dao.DataSource;
import bitcamp.java89.ems2.dao.TeacherDao;

public class TeacherMysqlDao implements TeacherDao {
DataSource ds;
  
  //Singleton 패턴 - start
  private TeacherMysqlDao() {
    ds = DataSource.getInstance();
  }
 
  static TeacherMysqlDao instance;
 
  public static TeacherMysqlDao getInstance() {
    if (instance == null) {
      instance = new TeacherMysqlDao();
    }
    return instance;
  }
  // end - Singleton 패턴
 
  
  
  public boolean exist(int memberNo) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        
      PreparedStatement stmt = con.prepareStatement(
          "select count(*) "
          + "from tcher left outer join memb on memb.mno=tcher.tno "
          + "where tno=?"); ) {
      
      stmt.setInt(1, memberNo);
      ResultSet rs = stmt.executeQuery();
      
      rs.next();
      int count = rs.getInt(1);
      rs.close();
      
      if (count > 0) {
        return true;
      } else {
        return false;
      }
      
    } finally {
      ds.returnConnection(con);
    }
  }
}
  
  
  
