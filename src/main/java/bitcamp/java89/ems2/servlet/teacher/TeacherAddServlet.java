package bitcamp.java89.ems2.servlet.teacher;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Photo;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.listener.ContextLoaderListener;
import bitcamp.java89.ems2.util.MultipartUtil;

@WebServlet("/teacher/add")
public class TeacherAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {

    try {
      Map<String, String> dataMap = MultipartUtil.parse(request);
      Teacher teacher = new Teacher();
      teacher.setEmail(dataMap.get("email"));
      teacher.setPassword(dataMap.get("password"));
      teacher.setName(dataMap.get("name"));
      teacher.setTel(dataMap.get("tel"));
      teacher.setHomepage(dataMap.get("homepage"));
      teacher.setFacabook(dataMap.get("facebook"));
      teacher.setTwitter(dataMap.get("twitter"));
      
      ArrayList<Photo> photoList = new ArrayList<>();
      photoList.add(new Photo(dataMap.get("photoPath1")));
      photoList.add(new Photo(dataMap.get("photoPath2")));
      photoList.add(new Photo(dataMap.get("photoPath3")));
      
      teacher.setPhotoList(photoList);


      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='Refresh' content='1;url=list'>");
      out.println("<title>강사관리-등록</title>");
      out.println("</head>");
      out.println("<body>");

      RequestDispatcher rd = request.getRequestDispatcher("/header");
      rd.include(request, response);
      
      
      out.println("<h1>등록 결과</h1>");

      TeacherDao teacherDao = (TeacherDao)ContextLoaderListener.applicationContext.getBean("teacherDao");

      if (teacherDao.exist(dataMap.get("email"))) {
        throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
      }

      MemberDao memberDao = (MemberDao)ContextLoaderListener.applicationContext.getBean("memberDao");

      if (!memberDao.exist(teacher.getEmail())) {
        memberDao.insert(teacher);
      } else {
        Member member = memberDao.getOne(teacher.getEmail());
        teacher.setMemberNo(member.getMemberNo());
      }

      teacherDao.insert(teacher);
      out.println("<p>등록하였습니다.</p>");

      rd = request.getRequestDispatcher("/footer");
      rd.include(request, response);
      
      

      out.println("</body>");
      out.println("</html>");

    } catch (Exception e) {
      request.setAttribute("error", e);
      
      RequestDispatcher rd = request.getRequestDispatcher("/error");
      rd.forward(request, response);
      return;
    }

  }
}
