package bitcamp.java89.ems2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    
  }

  
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    
    System.out.println("CharacterEncodingFilter.doFilter()");
    // 다음 필터나 서블릿 실행 전! 각각의 서블릿에서 다음 코드를 실행 필요 없다.
    request.setCharacterEncoding("UTF-8");
    
    chain.doFilter(request, response);
    
  }

  
  @Override
  public void destroy() {
    
  }
  
}
