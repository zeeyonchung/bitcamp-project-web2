package bitcamp.java89.ems2.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.annotation.RequestMapping;
import bitcamp.java89.ems2.dao.ManagerDao;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.StudentDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Photo;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller
public class TeacherControl {
  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;
  @Autowired ManagerDao managerDao;
  @Autowired TeacherDao teacherDao;

  
  
  
  
  @RequestMapping("/teacher/list.do")
  public String list(HttpServletRequest request, HttpServletResponse response) throws Exception {
    ArrayList<Teacher> list = teacherDao.getList();
    request.setAttribute("teachers", list);
    request.setAttribute("title", "강사관리-목록");
    request.setAttribute("contentPage", "/teacher/list.jsp");
    
    return "/main.jsp";
  }
  
  
  @RequestMapping("/teacher/detail.do")
  public String detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));

    Teacher teacher = teacherDao.getOne(memberNo);

    request.setAttribute("teacher", teacher);
    
    if (teacher == null) {
      throw new Exception("해당 아이디의 학생이 없습니다.");
    }

    List<Photo> photoList = teacher.getPhotoList();
    
    request.setAttribute("photoList", photoList);
    request.setAttribute("title", "강사관리-상세보기");
    request.setAttribute("contentPage", "/teacher/detail.jsp");
    
    return "/main.jsp";
  }
  
  
  @RequestMapping("/teacher/delete.do")
  public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
    int memberNo = Integer.parseInt(request.getParameter("memberNo"));

    if (!teacherDao.exist(memberNo)) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }

    teacherDao.delete(memberNo);

    if (!managerDao.exist(memberNo) && !studentDao.exist(memberNo)) {
      memberDao.delete(memberNo);
    }

    return "redirect:list.do";
  }
  
  
  @RequestMapping("/teacher/add.do")
  public String add(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, String> dataMap = MultipartUtil.parse(request);
    Teacher teacher = new Teacher();
    teacher.setEmail(dataMap.get("email"));
    teacher.setPassword(dataMap.get("password"));
    teacher.setName(dataMap.get("name"));
    teacher.setTel(dataMap.get("tel"));
    teacher.setHomepage(dataMap.get("homepage"));
    teacher.setFacebook(dataMap.get("facebook"));
    teacher.setTwitter(dataMap.get("twitter"));
    
    ArrayList<Photo> photoList = new ArrayList<>();
    photoList.add(new Photo(dataMap.get("photoPath1")));
    photoList.add(new Photo(dataMap.get("photoPath2")));
    photoList.add(new Photo(dataMap.get("photoPath3")));
    
    teacher.setPhotoList(photoList);


    if (teacherDao.exist(dataMap.get("email"))) {
      throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
    }


    if (!memberDao.exist(teacher.getEmail())) {
      memberDao.insert(teacher);
    } else {
      Member member = memberDao.getOne(teacher.getEmail());
      teacher.setMemberNo(member.getMemberNo());
    }

    teacherDao.insert(teacher);

    return "redirect:list.do";
  }
  
  
  @RequestMapping("/teacher/update.do")
  public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Map<String, String> dataMap = MultipartUtil.parse(request);
    
    Teacher teacher = new Teacher();
    teacher.setMemberNo(Integer.parseInt(dataMap.get("memberNo")));
    teacher.setEmail(dataMap.get("email"));
    teacher.setPassword(dataMap.get("password"));
    teacher.setName(dataMap.get("name"));
    teacher.setTel(dataMap.get("tel"));
    teacher.setHomepage(dataMap.get("homepage"));
    teacher.setFacebook(dataMap.get("facebook"));
    teacher.setTwitter(dataMap.get("twitter"));
    
    ArrayList<Photo> photoList = new ArrayList<>();
    photoList.add(new Photo(dataMap.get("photoPath1")));
    photoList.add(new Photo(dataMap.get("photoPath2")));
    photoList.add(new Photo(dataMap.get("photoPath3")));
    
    teacher.setPhotoList(photoList);
    


    if (!teacherDao.exist(teacher.getMemberNo())) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }

    memberDao.update(teacher);
    teacherDao.update(teacher);

    return "redirect:list.do";
  }
  
}


