package bitcamp.java89.ems2.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;
  @Autowired ManagerDao managerDao;
  @Autowired TeacherDao teacherDao;

  
  public List<Teacher> getList() throws Exception {
    return teacherDao.getList();
  }
  
  
  public Teacher getDetail(int no) throws Exception {
    Teacher teacher = teacherDao.getOneWithPhoto(no);
    
    if (teacher == null) {
      throw new Exception("해당 아이디의 학생이 없습니다.");
    }
    
    return teacher;
  }
  
  
  public int add(Teacher teacher) throws Exception {
    if (teacherDao.count(teacher.getEmail()) > 0) {
      throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
    }
    
    if (memberDao.count(teacher.getEmail()) == 0) {
      memberDao.insert(teacher);
    } else {
      Member member = memberDao.getOne(teacher.getEmail());
      teacher.setMemberNo(member.getMemberNo());
    }

    int count = teacherDao.insert(teacher);
    
    if (teacher.getPhotoList().size() > 0) {
      teacherDao.insertPhotoList(teacher);
    }
    
    return count;
  }
  
  
  
  public int update(Teacher teacher) throws Exception {
    
    if (teacherDao.countByNo(teacher.getMemberNo()) == 0) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }
    
    memberDao.update(teacher);
    
    int count = teacherDao.update(teacher);
    
    teacherDao.deletePhotoList(teacher.getMemberNo());
    teacherDao.insertPhotoList(teacher);
    
    return count;
  }
  
  
  public int delete(int no) throws Exception {
    if (teacherDao.countByNo(no) == 0) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }
    
    teacherDao.deletePhotoList(no);
    int count = teacherDao.delete(no);
    
    if (managerDao.countByNo(no) == 0 && studentDao.countByNo(no) == 0) {
      memberDao.delete(no);
    }
    
    return count;
  }
  
  
}
