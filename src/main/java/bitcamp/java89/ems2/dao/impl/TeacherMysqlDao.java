package bitcamp.java89.ems2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Photo;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.util.DataSource;



//@Repository("teacherDao")
public class TeacherMysqlDao {
  @Autowired DataSource ds;




  public Teacher getOne(int memberNo) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            /*
             select name, tel, email, hmpg, fcbk, twit, tpno, path
             from tcher
             left outer join memb on memb.mno=tcher.tno
             left outer join tch_phot on tch_phot.tno=tcher.tno
             where tcher.tno=41
             */
            "select name, tel, email, hmpg, fcbk, twit, tpno, path "
                + "from tcher "
                + "left outer join memb on memb.mno=tcher.tno "
                + "left outer join tch_phot on tch_phot.tno=tcher.tno "
                + "where tcher.tno=?;");) {

      stmt.setInt(1, memberNo);
      ResultSet rs = stmt.executeQuery();

      Teacher teacher = null;
      ArrayList<Photo> photoList = new ArrayList<>();
      
      while (rs.next()) {
        if (teacher == null) {
          teacher = new Teacher();
          teacher.setMemberNo(memberNo);
          teacher.setName(rs.getString("name"));
          teacher.setTel(rs.getString("tel"));
          teacher.setEmail(rs.getString("email"));
          teacher.setHomepage(rs.getString("hmpg"));
          teacher.setFacebook(rs.getString("fcbk"));
          teacher.setTwitter(rs.getString("twit"));
        }
        
        if (rs.getString("path") != null) {
          photoList.add(new Photo()
                   .setNo(rs.getInt("tpno"))
                   .setFilePath(rs.getString("path")));
        }
        
      }
      
      rs.close();
      
      
      if (teacher != null) {
        teacher.setPhotoList(photoList);
      }
      
      return teacher;
      
    } finally {
      ds.returnConnection(con);
    }
  }




//  public void insert(Teacher teacher) throws Exception {
//    Connection con = ds.getConnection();
//    try (
//        PreparedStatement stmt = con.prepareStatement(
//            "insert into tcher(tno,hmpg,fcbk,twit) values(?, ?, ?, ?);"); ) {
//
//      stmt.setInt(1, teacher.getMemberNo());
//      stmt.setString(2, teacher.getHomepage());
//      stmt.setString(3, teacher.getFacebook());
//      stmt.setString(4, teacher.getTwitter());
//
//      stmt.executeUpdate();
//      
//      this.insertPhotoList(teacher);
//
//    } finally {
//      ds.returnConnection(con);
//    }
//  }
//
//
//  public void insertPhotoList(Teacher teacher) throws Exception {
//    Connection con = ds.getConnection();
//    try (
//        PreparedStatement stmt = con.prepareStatement(
//            "insert into tch_phot(tno, path) values(?, ?);"); ) {
//
//      List<Photo> photoList = teacher.getPhotoList();
//
//      for (Photo photo : photoList) {
//        if (photo.getFilePath() == null) {
//          continue;
//        }
//
//        stmt.setInt(1, teacher.getMemberNo());
//        stmt.setString(2, photo.getFilePath());
//
//        stmt.executeUpdate();
//      }
//
//
//    } finally {
//      ds.returnConnection(con);
//    }
//  }



  public void update(Teacher teacher) throws Exception {
    Connection con = ds.getConnection(); // 커넥션풀에서 한 개의 Connection 객체를 임대한다.
    try (
        PreparedStatement stmt = con.prepareStatement(
            "update tcher set "
                + "hmpg=?, fcbk=?, twit=? "
                + "where tno=?"); ) {

      stmt.setString(1, teacher.getHomepage());
      stmt.setString(2, teacher.getFacebook());
      stmt.setString(3, teacher.getTwitter());
      stmt.setInt(4, teacher.getMemberNo());

      stmt.executeUpdate();
      
      
      this.deletePhotoList(teacher.getMemberNo());
      this.insertPhotoList(teacher);

    } finally {
      ds.returnConnection(con);
    }
  }
  
  
  public void deletePhotoList(int memberNo) throws Exception {
    Connection con = ds.getConnection(); 
    try (
      PreparedStatement stmt = con.prepareStatement(
          "delete from tch_phot where tno=?"); ) {
      
      stmt.setInt(1, memberNo);
      
      stmt.executeUpdate();
      
    } finally {
      ds.returnConnection(con);
    }
  }
  
  
}



