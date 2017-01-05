package bitcamp.java89.ems2.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bitcamp.java89.ems2.context.RequestHandlerMapping;
import bitcamp.java89.ems2.context.RequestHandlerMapping.RequestHandler;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  ApplicationContext applicationContext;
  RequestHandlerMapping handlerMapping;
  
  @Override
  public void init() throws ServletException {
    applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
    
    String[] names = applicationContext.getBeanDefinitionNames();
    ArrayList<Object> objList = new ArrayList<>();
    for(String name : names) {
      objList.add(applicationContext.getBean(name));
    }
    
    handlerMapping = new RequestHandlerMapping(objList);
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    try {
      String servletPath = request.getServletPath();

      // RequestHandlerMapping 객체에서 클라이언트 요청을 처리할 메서드 정보를 찾는다.
      RequestHandler requestHandler = null;
      try {
        requestHandler = handlerMapping.getRequestHandler(servletPath);
      } catch (Exception e) {}
      
      
      // 요청을 처리할 메서드를 찾았다면 호출한다.
      String viewUrl = null;
      if (requestHandler != null) {
        viewUrl = (String)requestHandler.method.invoke(requestHandler.obj, request, response);
      } else {
        viewUrl = servletPath.replaceAll(".do", ".jsp");
      }
      if (viewUrl.startsWith("redirect:")) {
        response.sendRedirect(viewUrl.substring(9));
        
      } else {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
        rd.include(request, response);
      }

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("title", "에러");
      request.setAttribute("contentPage", "/error.jsp");
      RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
      rd.forward(request, response);
      return;
    }

  }
}

