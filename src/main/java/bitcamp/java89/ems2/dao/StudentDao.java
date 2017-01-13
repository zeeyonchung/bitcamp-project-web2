package bitcamp.java89.ems2.dao;

import java.util.ArrayList;

import bitcamp.java89.ems2.domain.Student;

public interface StudentDao {
  ArrayList<Student> getList() throws Exception;
  Student getOne(int memberNo) throws Exception;
  int count(String email) throws Exception;
  int countByNo(int memberNo) throws Exception;
  void insert(Student student) throws Exception;
  void update(Student student) throws Exception;
  void delete(int memberNo) throws Exception;
}
