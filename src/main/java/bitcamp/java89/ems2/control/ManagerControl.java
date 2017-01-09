package bitcamp.java89.ems2.control;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Manager;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller
public class ManagerControl {
  @Autowired MemberDao memberDao;
  @Autowired ManagerDao managerDao;
  @Autowired TeacherDao teacherDao;
  @Autowired StudentDao studentDao;
  
  @RequestMapping("/manager/list.do")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ArrayList<Manager> list = managerDao.getList();
    request.setAttribute("managers", list);
    request.setAttribute("title", "매니저관리-목록");
    request.setAttribute("contentPage", "/manager/list.jsp");

    return "/main.jsp";
  }
  
  
  @RequestMapping("/manager/detail.do")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));

    Manager manager = managerDao.getOne(memberNo);
    
    request.setAttribute("manager", manager);
    request.setAttribute("title", "매니저관리-상세보기");
    request.setAttribute("contentPage", "/manager/detail.jsp");

    if (manager == null) {
      throw new Exception("해당 아이디의 학생이 없습니다.");
    }

    return "/main.jsp";
  }
  
  
  @RequestMapping("/manager/delete.do")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
  
  
  @RequestMapping("/manager/add.do")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, String> dataMap = MultipartUtil.parse(request);
    Manager manager = new Manager();
    manager.setEmail(dataMap.get("email"));
    manager.setPassword(dataMap.get("password"));
    manager.setName(dataMap.get("name"));
    manager.setTel(dataMap.get("tel"));
    manager.setPosition(dataMap.get("position"));
    manager.setFax(dataMap.get("fax"));
    manager.setPhotoPath(dataMap.get("photoPath"));


    if (managerDao.exist(dataMap.get("email"))) {
      throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
    }

    if (!memberDao.exist(manager.getEmail())) {
      memberDao.insert(manager);
    } else {
      Member member = memberDao.getOne(manager.getEmail());
      manager.setMemberNo(member.getMemberNo());
    }

    managerDao.insert(manager);
   
    return "redirect:list.do";
  }
  
  
  @RequestMapping("/manager/update.do")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, String> dataMap = MultipartUtil.parse(request);
    Manager manager = new Manager();
    manager.setMemberNo(Integer.parseInt(dataMap.get("memberNo")));
    manager.setEmail(dataMap.get("email"));
    manager.setPassword(dataMap.get("password"));
    manager.setName(dataMap.get("name"));
    manager.setTel(dataMap.get("tel"));
    manager.setPosition(dataMap.get("position"));
    manager.setFax(dataMap.get("fax"));
    manager.setPhotoPath(dataMap.get("photoPath"));

    response.setContentType("text/html;charset=UTF-8");


    if (!managerDao.exist(manager.getMemberNo())) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }

    memberDao.update(manager);
    managerDao.update(manager);

    return "redirect:list.do";
  }
  
}


