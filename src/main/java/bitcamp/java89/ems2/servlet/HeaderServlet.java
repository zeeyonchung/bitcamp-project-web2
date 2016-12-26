package bitcamp.java89.ems2.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.domain.Member;

@WebServlet("/header")
public class HeaderServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
 
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<div id='header' style='background-color:#DCF7F7; height:60px;"
          + "position:relative;'>");
      
      out.println("<div style='width:300px; height:38px;"
          + "position:absolute; left:0px; top:0px;'>");
      out.println("<img src='../image/n.jpg' height='50' "
          + "style='float:left; margin-top:6px; margin-left:8px'> ");
      out.println("<div style='color:gray; font-weight:bold; "
          + "margin-left:70px; padding-top:15px; font-family:돋움체, sans-serif; font-size:x-large;'>"
          + "교육센터관리시스템</div>");
      
      out.println("</div>");
      
      out.println("<div style='width:200px; height:38px;"
          + "position:absolute; right:0px; top:0px; margin-right:10px;'>");
      Member member = (Member)request.getSession().getAttribute("member");
      if (member == null) {
        out.println("<a href='../auth/login' style='position:absolute; right:0px; top:15px;'>로그인</a>");
      } else {
        out.printf("<span style='position:absolute; right:76px; top:16px;'>%s</span>\n", member.getName());
        out.println("<a href='../auth/logout' style='position:absolute; right:0px; top:15px;'>로그아웃</a>");
      }
      out.println("</div>");
      
      out.println("</div>");
      
      
    }
   
  }

