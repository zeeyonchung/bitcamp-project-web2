package bitcamp.java89.ems2.servlet.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.StudentMysqlDao;
import bitcamp.java89.ems2.domain.Student;

@WebServlet("/student/update")
public class StudentUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    
    Student student = new Student();
    student.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
    student.setName(request.getParameter("name"));
    student.setTel(request.getParameter("tel"));
    student.setEmail(request.getParameter("email"));
    student.setWorking(request.getParameter("working").equals("Y")? true:false);
    student.setPassword(request.getParameter("password"));
    student.setGrade(request.getParameter("grade"));
    student.setSchoolName(request.getParameter("schoolName"));
    student.setDetailAddress(request.getParameter("datailAddress"));
    
    response.setHeader("Refresh", "1;url=list");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>학생관리-변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>학생 결과</h1>");
    
    try {
      StudentMysqlDao studentDao = StudentMysqlDao.getInstance();
      
      if (!studentDao.existMemberNo(Integer.parseInt(request.getParameter("memberNo")))) {
        throw new Exception("사용자를 찾지 못했습니다.");
      }
      
      studentDao.update(student);
      out.println("<p>변경 하였습니다.</p>");
      
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    
    out.println("</body>");
    out.println("</html>");
  }
  
}
