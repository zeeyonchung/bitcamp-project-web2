package bitcamp.java89.ems2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Manager;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.service.ManagerService;

@Service
public class ManagerServiceImpl implements ManagerService {
  @Autowired MemberDao memberDao;
  @Autowired ManagerDao managerDao;
  @Autowired TeacherDao teacherDao;
  @Autowired StudentDao studentDao;
  
  
  public List<Manager> getList() throws Exception {
    return managerDao.getList();
  }
  
  
  public Manager getDetail(int no) throws Exception {
    return managerDao.getOne(no);
  }
  
  
  public int add(Manager manager) throws Exception {
    if (managerDao.count(manager.getEmail()) > 0) {
      throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
    }

    if (memberDao.count(manager.getEmail()) == 0) {
      memberDao.insert(manager);
    } else {
      Member member = memberDao.getOne(manager.getEmail());
      manager.setMemberNo(member.getMemberNo());
    }
    
    return managerDao.insert(manager);
  }
  
  
  public int update(Manager manager) throws Exception {
    if (managerDao.countByNo(manager.getMemberNo()) == 0) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }

    memberDao.update(manager);
    return managerDao.update(manager);
  }
  
  
  public int delete(int no) throws Exception {
    if (managerDao.countByNo(no) == 0) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }
    
    int count = managerDao.delete(no);
    
    if (teacherDao.countByNo(no) == 0 && studentDao.countByNo(no) == 0) {
      memberDao.delete(no);
    }
    
    return count;
  }
  
  
}
