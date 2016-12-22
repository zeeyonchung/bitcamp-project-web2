package bitcamp.java89.ems2.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.domain.Manager;

@WebServlet("/manager/list")
public class ManagerListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>매니저관리-목록</title>");
      out.println("</head>");
      out.println("<body>");
      
      
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      out.println("<h1>매니저 정보</h1>");
      ManagerDao managerDao = (ManagerDao)this.getServletContext().getAttribute("managerDao");
      ArrayList<Manager> list = managerDao.getList();

      out.println("<a href='form.html'>추가</a><br>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("  <th>회원번호</th>");
      out.println("  <th>이름</th>");
      out.println("  <th>전화</th>");
      out.println("  <th>이메일</th>");
      out.println("  <th>직급</th>");
      out.println("  <th>팩스</th>");
      out.println("  <th>사진</th>");
      out.println("</tr>");

      for (Manager manager : list) {
        out.println("<tr> ");
        out.printf("  <td>%d</td>"
            + "<td><a href='detail?memberNo=%1$s'>%s</a></td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td>%s</td>"
            + "<td>%s</td>\n",
            manager.getMemberNo(),
            manager.getName(),
            manager.getTel(),
            manager.getEmail(),

            manager.getPosition(),
            manager.getFax(),
            manager.getPhotoPath());
        out.println("</tr>");
      }

      out.println("</table>");
      
      
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


