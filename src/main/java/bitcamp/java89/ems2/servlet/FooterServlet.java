package bitcamp.java89.ems2.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/footer")
public class FooterServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
 
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
      
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      
      out.println("<div id='footer' style='background-color:#DCF7F7; height:28px; margin-top:50px;'>");
      out.println("<div style='color:gray; margin-top:10px; padding-top:5px; font-family:돋움체, sans-serif; "
          + "text-align:center; font-weight:lighter;'>@2016 비트캠프 자바89기</div>");
      out.println("</div>");
      
    }
   
  }

