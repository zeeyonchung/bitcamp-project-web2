package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bitcamp.java89.ems2.dao.DataSource;
import bitcamp.java89.ems2.dao.ManagerDao;

public class ManagerMysqlDao implements ManagerDao {
DataSource ds;
  
  //Singleton 패턴 - start
  private ManagerMysqlDao() {
    ds = DataSource.getInstance();
  }
 
  static ManagerMysqlDao instance;
 
  public static ManagerMysqlDao getInstance() {
    if (instance == null) {
      instance = new ManagerMysqlDao();
    }
    return instance;
  }
  // end - Singleton 패턴
 

  
  public boolean exist(int memberNo) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        
      PreparedStatement stmt = con.prepareStatement(
          "select count(*) "
          + "from mgr left outer join memb on memb.mno=mgr.mrno "
          + "where mrno=?"); ) {
      
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
