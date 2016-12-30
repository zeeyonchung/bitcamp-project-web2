package bitcamp.java89.ems2.servlet.manager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.domain.Manager;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/manager/detail")
public class ManagerDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      int memberNo = Integer.parseInt(request.getParameter("memberNo"));

      response.setContentType("text/html;charset=UTF-8");
      
      
      
      ManagerDao managerDao = (ManagerDao)ContextLoaderListener.applicationContext.getBean("managerDao");
      Manager manager = managerDao.getOne(memberNo);
      
      request.setAttribute("manager", manager);

      if (manager == null) {
        throw new Exception("해당 아이디의 학생이 없습니다.");
      }

      
      RequestDispatcher rd = request.getRequestDispatcher("/manager/detail.jsp");
      rd.include(request, response);

      
      
    } catch (Exception e) {
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }

  }
}
