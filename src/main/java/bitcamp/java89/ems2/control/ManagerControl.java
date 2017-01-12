package bitcamp.java89.ems2.control;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Manager;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller
public class ManagerControl {
  @Autowired ServletContext sc;
  @Autowired MemberDao memberDao;
  @Autowired ManagerDao managerDao;
  @Autowired TeacherDao teacherDao;
  @Autowired StudentDao studentDao;
  
  @RequestMapping("/manager/list")
  public String list(Model model) throws Exception {
    ArrayList<Manager> list = managerDao.getList();
    model.addAttribute("managers", list);
    model.addAttribute("title", "매니저관리-목록");
    model.addAttribute("contentPage", "/manager/list.jsp");

    return "main";
  }
  
  
  @RequestMapping("/manager/detail")
  public String detail(int memberNo, Model model) throws Exception {
    Manager manager = managerDao.getOne(memberNo);
    
    model.addAttribute("manager", manager);
    model.addAttribute("title", "매니저관리-상세보기");
    model.addAttribute("contentPage", "/manager/detail.jsp");

    if (manager == null) {
      throw new Exception("해당 아이디의 학생이 없습니다.");
    }

    return "main";
  }
  
  
  @RequestMapping("/manager/delete")
  public String delete(int memberNo) throws Exception {
    
    if (!managerDao.exist(memberNo)) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }

    managerDao.delete(memberNo);

    if (!teacherDao.exist(memberNo) && !studentDao.exist(memberNo)) {
      memberDao.delete(memberNo);
    }

    return "redirect:list.do";
  }
  
  
  @RequestMapping("/manager/add")
  public String add(Manager manager, MultipartFile photo) throws Exception {

    if (managerDao.exist(manager.getEmail())) {
      throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
    }

    if (!memberDao.exist(manager.getEmail())) {
      memberDao.insert(manager);
    } else {
      Member member = memberDao.getOne(manager.getEmail());
      manager.setMemberNo(member.getMemberNo());
    }
    
    if (photo.getSize() > 0) {
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      manager.setPhotoPath(newFilename);
    }

    managerDao.insert(manager);
   
    return "redirect:list.do";
  }
  
  
  @RequestMapping("/manager/update")
  public String update(Manager manager, MultipartFile photo) throws Exception {

    if (!managerDao.exist(manager.getMemberNo())) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }

    memberDao.update(manager);
    
    if (photo.getSize() > 0) {
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      manager.setPhotoPath(newFilename);
    }
    
    managerDao.update(manager);

    return "redirect:list.do";
  }
  
}


