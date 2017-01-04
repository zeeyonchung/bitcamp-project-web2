package bitcamp.java89.ems2.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface PageController {
  String service(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
