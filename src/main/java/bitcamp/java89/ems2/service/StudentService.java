package bitcamp.java89.ems2.service;

import java.util.List;

import bitcamp.java89.ems2.domain.Student;

public interface StudentService {
  public List<Student> getList() throws Exception;
  public Student getDatail(int no) throws Exception;
  public int add(Student student) throws Exception;
  public int delete(int no) throws Exception;
  public int update(Student student) throws Exception;
}
