package bitcamp.java89.ems2.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class ContextLoaderListener implements ServletContextListener {
  public static ApplicationContext applicationContext;


  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {

      applicationContext = new FileSystemXmlApplicationContext (
          "file:" + sce.getServletContext().getRealPath("/WEB-INF/conf/application-context.xml"));


      System.out.println("ContextLoaderListener.contextInitialized() 실행!");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {

  }

}
