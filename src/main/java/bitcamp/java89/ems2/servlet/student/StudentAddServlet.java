package bitcamp.java89.ems2.servlet.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.impl.MemberMysqlDao;
import bitcamp.java89.ems2.dao.impl.StudentMysqlDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Student;

@WebServlet("/student/add")
public class StudentAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      request.setCharacterEncoding("UTF-8");

      Student student = new Student();
      student.setEmail(request.getParameter("email"));
      student.setPassword(request.getParameter("password"));
      student.setName(request.getParameter("name"));
      student.setTel(request.getParameter("tel"));
      student.setWorking(Boolean.parseBoolean(request.getParameter("working")));
      student.setGrade(request.getParameter("grade"));
      student.setSchoolName(request.getParameter("schoolName"));
      student.setPhotoPath(request.getParameter("photoPath"));

      response.setHeader("Refresh", "1;url=list");
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>학생관리-등록</title>");
      out.println("</head>");
      out.println("<body>");
      
      
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      
      out.println("<h1>등록 결과</h1>");

      StudentMysqlDao studentDao = StudentMysqlDao.getInstance();

      if (studentDao.exist(request.getParameter("email"))) {
        throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
      }

      MemberMysqlDao memberDao = MemberMysqlDao.getInstance();

      if (!memberDao.exist(student.getEmail())) {
        memberDao.insert(student);
      } else {
        Member member = memberDao.getOne(student.getEmail());
        student.setMemberNo(member.getMemberNo());
      }

      studentDao.insert(student);
      out.println("<p>등록하였습니다.</p>");
      
      
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }


  }
}
