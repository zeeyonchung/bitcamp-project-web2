package bitcamp.java89.ems2.control.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;

@Controller("/manager/delete.do")
public class ManagerDeleteControl implements PageController {
  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;
  @Autowired ManagerDao managerDao;
  @Autowired TeacherDao teacherDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));

    if (!managerDao.exist(memberNo)) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }

    managerDao.delete(memberNo);

    if (!teacherDao.exist(memberNo) && !studentDao.exist(memberNo)) {
      memberDao.delete(memberNo);
    }

    return "redirect:list.do";
  }
}
