package bitcamp.java89.ems2.control.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.domain.Student;

@Controller("/student/detail.do")
public class StudentDetailControl implements PageController {
  @Autowired StudentDao studentDao;
  
  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int memberNo = Integer.parseInt(request.getParameter("memberNo"));

    Student student = studentDao.getOne(memberNo);
    
    request.setAttribute("student", student);

    if (student == null) {
      throw new Exception("해당 아이디의 학생이 없습니다.");
    }

    return "/student/detail.jsp";
  }
}
