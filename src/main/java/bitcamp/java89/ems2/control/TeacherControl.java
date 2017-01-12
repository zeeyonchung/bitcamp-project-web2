package bitcamp.java89.ems2.control;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Photo;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller
public class TeacherControl {
  @Autowired ServletContext sc;
  @Autowired MemberDao memberDao;
  @Autowired StudentDao studentDao;
  @Autowired ManagerDao managerDao;
  @Autowired TeacherDao teacherDao;

  
  
  
  
  @RequestMapping("/teacher/list")
  public String list(Model model) throws Exception {
    ArrayList<Teacher> list = teacherDao.getList();
    model.addAttribute("teachers", list);
    model.addAttribute("title", "강사관리-목록");
    model.addAttribute("contentPage", "/teacher/list.jsp");
    
    return "main";
  }
  
  
  @RequestMapping("/teacher/detail")
  public String detail(int memberNo, Model model) throws Exception {
    
    Teacher teacher = teacherDao.getOne(memberNo);
    
    model.addAttribute("teacher", teacher);
    
    if (teacher == null) {
      throw new Exception("해당 아이디의 학생이 없습니다.");
    }

    List<Photo> photoList = teacher.getPhotoList();
    
    model.addAttribute("photoList", photoList);
    model.addAttribute("title", "강사관리-상세보기");
    model.addAttribute("contentPage", "/teacher/detail.jsp");
    
    return "main";
  }
  
  
  @RequestMapping("/teacher/delete")
  public String delete(int memberNo) throws Exception {

    if (!teacherDao.exist(memberNo)) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }

    teacherDao.delete(memberNo);

    if (!managerDao.exist(memberNo) && !studentDao.exist(memberNo)) {
      memberDao.delete(memberNo);
    }

    return "redirect:list.do";
  }
  
  
  @RequestMapping("/teacher/add")
  public String add(Teacher teacher, MultipartFile[] photo) throws Exception {
    
    if (teacherDao.exist(teacher.getEmail())) {
      throw new Exception("같은 사용자 아이디가 존재합니다. 등록을 취소합니다.");
    }
    
    
    if (!memberDao.exist(teacher.getEmail())) {
      memberDao.insert(teacher);
    } else {
      Member member = memberDao.getOne(teacher.getEmail());
      teacher.setMemberNo(member.getMemberNo());
    }
    
    ArrayList<Photo> photoList = new ArrayList<>();

    for (MultipartFile file : photo) {
      if (file.getSize() > 0) {
        String newFilename = MultipartUtil.generateFilename();
        file.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
        photoList.add(new Photo(newFilename));
      }
    }
    
    teacher.setPhotoList(photoList);



    teacherDao.insert(teacher);

    return "redirect:list.do";
  }
  
  
  @RequestMapping("/teacher/update")
  public String update(Teacher teacher, MultipartFile[] photo) throws Exception {
    
    
    if (!teacherDao.exist(teacher.getMemberNo())) {
      throw new Exception("사용자를 찾지 못했습니다.");
    }
    
    memberDao.update(teacher);
    
    ArrayList<Photo> photoList = new ArrayList<>();
    
    for (MultipartFile file : photo) {
      if (file.getSize() > 0) {
        String newFilename = MultipartUtil.generateFilename();
        file.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
        photoList.add(new Photo(newFilename));
      }
    }

    teacher.setPhotoList(photoList);

    teacherDao.update(teacher);

    return "redirect:list.do";
  }
  
}


