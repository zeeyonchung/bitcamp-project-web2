package bitcamp.java89.ems2.servlet.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.domain.Member;


@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    try {

      String email = "";
      Cookie[] cookies = request.getCookies();

      if (cookies != null) {
        for (Cookie cookie : cookies) {
          if (cookie.getName().equals("email")) {
            email = cookie.getValue();
            break;
          }
        }
      }


      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();



      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>로그인</title>");
      out.println("</head>");
      out.println("<body>");
      
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);

      out.println("<h1>로그인</h1>");
      out.println("<form action='login' method='POST'>");
      out.println("<table border='1'>");
      out.printf("<tr><th>이메일</th><td><input name='email' type='text' placeholder='예)hong@gmail.com' value=%s></td></tr>", email);
      out.println("<tr><th>암호</th><td><input name='password' type='password'></td></tr>");
      out.println("<tr><th></th><td><input name='saveEmail' type='checkbox' checked>이메일 저장</td></tr>");
      out.println("</table>");
      out.println("<button type='submit'>로그인</button>");
      out.println("</form>");

      out.println("</body>");
      
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);
      
      
      out.println(" </html>");


    } catch (Exception e) {
      request.setAttribute("error", e);

      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }


  }




  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {


      String saveEmail = request.getParameter("saveEmail");
      String email = request.getParameter("email");
      String password = request.getParameter("password");


      if (saveEmail != null) {
        //쿠키를 브라우저에게 보낸다.
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(60 * 60 * 24 * 15);
        response.addCookie(cookie);

      } else {
        //기존에 보낸 쿠키를 제거하라고 브라우저에게 응답한다.
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

      }

      
      MemberDao memberDao = (MemberDao)this.getServletContext().getAttribute("memberDao");

      if (memberDao.exist(email, password)) {
        Member member = memberDao.getOne(email); //로그인한 사용자 정보를 가져와서
        request.getSession().setAttribute("member", member); //HttpSession에 저장한다.
        response.sendRedirect("../student/list");
        return;
      }
      
      response.setHeader("Refresh", "2;url=login");
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();


      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>로그인</title>");
      out.println("</head>");
      out.println("<body>");
      
      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);

      out.println("<h1>로그인 실패</h1>");
//      out.printf("email: %s<br>\n", email);
//      out.printf("password: %s<br>\n", password);
//      out.printf("saveEmail: %s<br>\n", saveEmail);
      
      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);
      
      
      out.println("</body>");
      out.println(" </html>");

    } catch (Exception e) {
      request.setAttribute("error", e);

      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }


  }




}
