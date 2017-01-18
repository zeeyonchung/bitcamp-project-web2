package bitcamp.java89.ems2.control;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.service.StudentService;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller
public class StudentControl {
  @Autowired ServletContext sc;
  @Autowired StudentService studentService;
  
  @RequestMapping("/student/list")
  public String list(Model model) throws Exception {
    List<Student> list = studentService.getList();
    model.addAttribute("students", list);
    model.addAttribute("title", "학생관리-목록");
    model.addAttribute("contentPage", "/student/list.jsp");
    
    return "main";
  }
  
  
  @RequestMapping("/student/detail")
  public String detail(int memberNo, Model model) throws Exception {

    Student student = studentService.getDatail(memberNo);
    
    if (student == null) {
      throw new Exception("해당 아이디의 학생이 없습니다.");
    }
    
    model.addAttribute("student", student);
    model.addAttribute("title", "학생관리-상세보기");
    model.addAttribute("contentPage", "/student/detail.jsp");

    return "main";
  }
  
  
  @RequestMapping("/student/add")
  public String add(Student student, MultipartFile photo) throws Exception {

    if (photo.getSize() > 0) {
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      student.setPhotoPath(newFilename);
    }
    
    studentService.add(student);
    
    return "redirect:list.do";
  }
  
  
  @RequestMapping("/student/delete")
  public String delete(int memberNo) throws Exception {
    
    studentService.delete(memberNo);

    return "redirect:list.do";
  }
  
  
  @RequestMapping("/student/update")
  public String update(Student student, MultipartFile photo) throws Exception {

    if (photo.getSize() > 0) {
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      student.setPhotoPath(newFilename);
    }
    
    studentService.update(student);
    
    return "redirect:list.do";
  }
  
}


