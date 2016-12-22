// 역할: 다른 서블릿이 사용할 DAO 객체를 준비한다.
// 클라이언트가 직접 호출할 일은 없다.
package bitcamp.java89.ems2.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import bitcamp.java89.ems2.dao.DataSource;
import bitcamp.java89.ems2.dao.impl.ManagerMysqlDao;
import bitcamp.java89.ems2.dao.impl.MemberMysqlDao;
import bitcamp.java89.ems2.dao.impl.StudentMysqlDao;
import bitcamp.java89.ems2.dao.impl.TeacherMysqlDao;

//@WebServlet 애노테이션이 필요 없다. web.xml에 설정.
public class ContextLoaderServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  //이 클래스는 다른 서블릿이 사용할 도구를 준비해야 하기 때문에
  //서블릿 객체가 생성될 때 호출되는 메서드를 오버라이딩 한다.
  
  
  @Override
  public void init() throws ServletException {
    
    try {
      //ServletContext: 공통보관소. 애플리케이션 당 하나가 생긴다.
      ServletContext sc = this.getServletContext();

      DataSource ds = new DataSource();
      
      MemberMysqlDao memberDao = new MemberMysqlDao();
      memberDao.setDataSource(ds);
      sc.setAttribute("memberDao", memberDao);
      
      ManagerMysqlDao managerDao = new ManagerMysqlDao();
      managerDao.setDataSource(ds);
      sc.setAttribute("managerDao", managerDao);
      
      TeacherMysqlDao teacherDao = new TeacherMysqlDao();
      teacherDao.setDataSource(ds);
      sc.setAttribute("teacherDao", teacherDao);
      
      StudentMysqlDao studentDao = new StudentMysqlDao();
      studentDao.setDataSource(ds);
      sc.setAttribute("studentDao", studentDao);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    
  }
}
