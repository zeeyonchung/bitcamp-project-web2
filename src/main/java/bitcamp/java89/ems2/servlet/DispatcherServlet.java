package bitcamp.java89.ems2.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.listener.ContextLoaderListener;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      // 클라이언트가 요청한 서블릿 주소
      String servletPath = request.getServletPath();
      
      // 내부 서블릿이나 JSP에서 include 하는 경우, 기존 ServletRequest를 사용하기 때문에
      // getServletPath()가 리턴한 값이 이전 값과 같다. 
      // 그래서 내부에서 include 또는 forward 한 경우를 고려해서 
      // 파라미터로 따로 전달된 서블릿 경로를 사용하자.
      
//      if (request.getParameter("servletPath") != null) {
//        servletPath = request.getParameter("servletPath");
//      }

      // 스프링 IoC 컨테이너에서 서블릿 경로에 해당하는 객체를 찾는다.
      PageController pageController = null;
      
      try {
          pageController = (PageController)ContextLoaderListener.applicationContext.getBean(servletPath);
      } catch (Exception e) {}
      
      
      String viewUrl;
      if (pageController != null) {
        // 페이지 컨트롤러를 호출하여 작업을 실행시킨다.
        viewUrl = pageController.service(request, response);
      } else {
        viewUrl = servletPath.replaceAll(".do", ".jsp");
      }
      
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
        
      } else {
        // 페이지 컨트롤러가 리턴한 뷰 컴포넌트(JSP)로 화면 출력을 위임한다.
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
        rd.include(request, response);
      }

    } catch (Exception e) {
      request.setAttribute("error", e);
      RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
      rd.forward(request, response);
      return;
    }

  }
}

