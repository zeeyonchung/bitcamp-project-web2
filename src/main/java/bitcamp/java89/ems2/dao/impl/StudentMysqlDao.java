package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bitcamp.java89.ems2.dao.DataSource;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Student;

public class StudentMysqlDao implements StudentDao {
DataSource ds;
  
  //Singleton 패턴 - start
  private StudentMysqlDao() {
    ds = DataSource.getInstance();
  }
 
  static StudentMysqlDao instance;
 
  public static StudentMysqlDao getInstance() {
    if (instance == null) {
      instance = new StudentMysqlDao();
    }
    return instance;
  }
  // end - Singleton 패턴
 
  public ArrayList<Student> getList() throws Exception {
    ArrayList<Student> list = new ArrayList<>();
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        
      PreparedStatement stmt = con.prepareStatement(
          "select mno, name, tel, email, work, lst_schl, schl_nm, path "
          + "from stud "
          + "left outer join memb on memb.mno=stud.sno");
        
      ResultSet rs = stmt.executeQuery(); ){
      
      while (rs.next()) { // 서버에서 레코드 한 개를 가져왔다면,
        Student student = new Student();
        student.setMemberNo(rs.getInt("mno"));
        student.setName(rs.getString("name"));
        student.setTel(rs.getString("tel"));
        student.setEmail(rs.getString("email"));
        student.setWorking(rs.getString("work").equals("Y") ? true : false);
        student.setGrade(rs.getString("lst_schl"));
        student.setSchoolName(rs.getString("schl_nm"));
        student.setPhotoPath(rs.getString("path"));
        list.add(student);
      }
    } finally {
      ds.returnConnection(con);
    }
    return list;
  }
  
  
  
  public Student getOne(int memberNo) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
      PreparedStatement stmt = con.prepareStatement(
          "select name, tel, email, work, lst_schl, schl_nm, path "
            + "from stud "
            + "left outer join memb on memb.mno=stud.sno "
            + "where mno=?");) {

      stmt.setInt(1, memberNo);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        Student student = new Student();
        student.setMemberNo(memberNo);
        student.setName(rs.getString("name"));
        student.setTel(rs.getString("tel"));
        student.setEmail(rs.getString("email"));
        student.setWorking(rs.getString("work").equals("Y") ? true : false);
        student.setGrade(rs.getString("lst_schl"));
        student.setSchoolName(rs.getString("schl_nm"));
        student.setPhotoPath(rs.getString("path"));
        return student;
        
      } else {
        rs.close();
        return null;
      }
    } finally {
      ds.returnConnection(con);
    }
  }
  
  
  public boolean exist(String email) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        
      PreparedStatement stmt = con.prepareStatement(
          "select count(*) "
          + "from stud left outer join memb on memb.mno=stud.sno "
          + "where email=?"); ) {
      
      stmt.setString(1, email);
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
  
  
  public boolean exist(int memberNo) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        
      PreparedStatement stmt = con.prepareStatement(
          "select count(*) "
          + "from stud left outer join memb on memb.mno=stud.sno "
          + "where sno=?"); ) {
      
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
  
  
  public void insert(Student student) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
      PreparedStatement stmt = con.prepareStatement(
          "insert into stud(sno,work,lst_schl,schl_nm,path) values(?, ?, ?, ?, ?);"); ) {
      
      stmt.setInt(1, student.getMemberNo());
      stmt.setString(2, student.isWorking() ? "Y" : "N");
      stmt.setString(3, student.getGrade());
      stmt.setString(4, student.getSchoolName());
      stmt.setString(5, student.getPhotoPath());
      
      stmt.executeUpdate();
      
    } finally {
      ds.returnConnection(con);
    }
  }
  
  
  
  public void update(Student student) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
      PreparedStatement stmt = con.prepareStatement(
          "update stud set "
          + "work=?, lst_schl=?, schl_nm=?, path=? "
          + "where sno=?"); ) {
      
      stmt.setString(1, student.isWorking() ? "Y" : "N");
      stmt.setString(2, student.getGrade());
      stmt.setString(3, student.getSchoolName());
      stmt.setString(4, student.getPhotoPath());
      stmt.setInt(5, student.getMemberNo());
      
      stmt.executeUpdate();
      
    } finally {
      ds.returnConnection(con);
    }
  }
  
  
  
  public void delete(int memberNo) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
      PreparedStatement stmt = con.prepareStatement(
          "delete from stud where sno=?"); ) {
      
      stmt.setInt(1, memberNo);
      
      stmt.executeUpdate();
    } finally {
      ds.returnConnection(con);
    }
  }
  
  
  
}
