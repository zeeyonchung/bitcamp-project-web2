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
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("/manager/list")
public class ManagerListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      
      out.println("<h1>매니저 정보</h1>");
      ManagerDao managerDao = (ManagerDao)ContextLoaderListener.applicationContext.getBean("managerDao");
      ArrayList<Manager> list = managerDao.getList();
      
      request.setAttribute("managers", list);

      RequestDispatcher rd = request.getRequestDispatcher("/manager/list.jsp");
      rd.include(request, response);
    
    } catch (Exception e) {
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }

  }
}


