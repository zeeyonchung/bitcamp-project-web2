package bitcamp.java89.ems2.dao;

import java.util.ArrayList;

import bitcamp.java89.ems2.domain.Manager;

public interface ManagerDao {
  boolean exist(int memberNo) throws Exception;
  ArrayList<Manager> getList() throws Exception;
  Manager getOne(int memberNo) throws Exception;
  boolean exist(String email) throws Exception;
  void insert(Manager manager) throws Exception;
  void update(Manager manager) throws Exception;
  void delete(int memberNo) throws Exception;
}
