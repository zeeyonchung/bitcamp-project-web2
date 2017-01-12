package bitcamp.java89.ems2.control;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller
public class StudentControl {
  @Autowired ServletContext sc;
  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;
  @Autowired ManagerDao managerDao;
  @Autowired TeacherDao teacherDao;
  
  
  @RequestMapping("/student/list")
  public String list(Model model) throws Exception {
    ArrayList<Student> list = studentDao.getList();
    model.addAttribute("students", list);
    model.addAttribute("title", "학생관리-목록");
    model.addAttribute("contentPage", "/student/list.jsp");
    
    return "main";
  }
  
  
  @RequestMapping("/student/detail")
  public String detail(int memberNo, Model model) throws Exception {

    Student student = studentDao.getOne(memberNo);
    
    model.addAttribute("student", student);
    model.addAttribute("title", "학생관리-상세보기");
    model.addAttribute("contentPage", "/student/detail.jsp");

    if (student == null) {
      throw new Exception("해당 아이디의 학생이 없습니다.");
    }

    return "main";
  }
  
  
  @RequestMapping("/student/add")
  public String add(Student student, MultipartFile photo) throws Exception {

    if (studentDao.exist(student.getEmail())) {
      throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
    }
    
    if (!memberDao.exist(student.getEmail())) {
      memberDao.insert(student);
    } else {
      Member member = memberDao.getOne(student.getEmail());
      student.setMemberNo(member.getMemberNo());
    }
    
    if (photo.getSize() > 0) {
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      student.setPhotoPath(newFilename);
    }
    
    studentDao.insert(student);
    
    return "redirect:list.do";
  }
  
  
  @RequestMapping("/student/delete")
  public String delete(int memberNo) throws Exception {

    if (!studentDao.exist(memberNo)) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }

    studentDao.delete(memberNo);

    if (!managerDao.exist(memberNo) && !teacherDao.exist(memberNo)) {
      memberDao.delete(memberNo);
    }

    return "redirect:list.do";
  }
  
  
  @RequestMapping("/student/update")
  public String update(Student student, MultipartFile photo) throws Exception {

    if (!studentDao.exist(student.getMemberNo())) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }
    memberDao.update(student);
    
    
    if (photo.getSize() > 0) {
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      student.setPhotoPath(newFilename);
    }
    
    
    studentDao.update(student);
    
    return "redirect:list.do";
  }
  
}


