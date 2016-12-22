package bitcamp.java89.ems2.dao;

import java.util.ArrayList;

import bitcamp.java89.ems2.domain.Student;

public interface StudentDao {
  boolean exist(int memberNo) throws Exception;
  ArrayList<Student> getList() throws Exception;
  Student getOne(int memberNo) throws Exception;
  boolean exist(String email) throws Exception;
  void insert(Student student) throws Exception;
  void update(Student student) throws Exception;
  void delete(int memberNo) throws Exception;
}
