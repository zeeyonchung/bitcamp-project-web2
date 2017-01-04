package bitcamp.java89.ems2.control.student;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller("/student/add.do")
public class StudentAddControl implements PageController {
  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
}
