package bitcamp.java89.ems2.control;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.annotation.RequestMapping;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller
public class StudentControl {
  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;
  @Autowired ManagerDao managerDao;
  @Autowired TeacherDao teacherDao;
  
  
  @RequestMapping("/student/list.do")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ArrayList<Student> list = studentDao.getList();
    request.setAttribute("students", list);
    request.setAttribute("title", "학생관리-목록");
    request.setAttribute("contentPage", "/student/list.jsp");
    
    return "/main.jsp";
  }
  
  
  @RequestMapping("/student/detail.do")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int memberNo = Integer.parseInt(request.getParameter("memberNo"));

    Student student = studentDao.getOne(memberNo);
    
    request.setAttribute("student", student);
    request.setAttribute("title", "학생관리-상세보기");
    request.setAttribute("contentPage", "/student/detail.jsp");

    if (student == null) {
      throw new Exception("해당 아이디의 학생이 없습니다.");
    }

    return "/main.jsp";
  }
  
  
  @RequestMapping("/student/add.do")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, String> dataMap = MultipartUtil.parse(request);
    Student student = new Student();
    student.setEmail(dataMap.get("email"));
    student.setPassword(dataMap.get("password"));
    student.setName(dataMap.get("name"));
    student.setTel(dataMap.get("tel"));
    student.setWorking(Boolean.parseBoolean(dataMap.get("working")));
    student.setGrade(dataMap.get("grade"));
    student.setSchoolName(dataMap.get("schoolName"));
    student.setPhotoPath(dataMap.get("photoPath"));


    if (studentDao.exist(request.getParameter("email"))) {
      throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
    }

    if (!memberDao.exist(student.getEmail())) {
      memberDao.insert(student);
    } else {
      Member member = memberDao.getOne(student.getEmail());
      student.setMemberNo(member.getMemberNo());
    }
    
    studentDao.insert(student);
    
    return "redirect:list.do";
  }
  
  
  @RequestMapping("/student/delete.do")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));


    if (!studentDao.exist(memberNo)) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }

    studentDao.delete(memberNo);

    if (!managerDao.exist(memberNo) && !teacherDao.exist(memberNo)) {
      memberDao.delete(memberNo);
    }

    return "redirect:list.do";
  }
  
  
  @RequestMapping("/student/update.do")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, String> dataMap = MultipartUtil.parse(request);
    Student student = new Student();
    student.setMemberNo(Integer.parseInt(dataMap.get("memberNo")));
    student.setEmail(dataMap.get("email"));
    student.setPassword(dataMap.get("password"));
    student.setName(dataMap.get("name"));
    student.setTel(dataMap.get("tel"));
    student.setWorking(Boolean.parseBoolean(dataMap.get("working")));
    student.setGrade(dataMap.get("grade"));
    student.setSchoolName(dataMap.get("schoolName"));
    student.setPhotoPath(dataMap.get("photoPath"));


    if (!studentDao.exist(student.getMemberNo())) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }
    memberDao.update(student);
    studentDao.update(student);
    
    return "redirect:list.do";
  }
  
}


