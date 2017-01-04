package bitcamp.java89.ems2.control.manager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.domain.Manager;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller("/manager/add.do")
public class ManagerAddControl implements PageController {
  @Autowired MemberDao memberDao;
  @Autowired ManagerDao managerDao;

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
}
