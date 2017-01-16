package bitcamp.java89.ems2.dao;

import java.util.ArrayList;

import bitcamp.java89.ems2.domain.Manager;

public interface ManagerDao {
  int countByNo(int memberNo) throws Exception;
  int count(String email) throws Exception;
  ArrayList<Manager> getList() throws Exception;
  Manager getOne(int memberNo) throws Exception;
  int insert(Manager manager) throws Exception;
  int update(Manager manager) throws Exception;
  int delete(int memberNo) throws Exception;
}
