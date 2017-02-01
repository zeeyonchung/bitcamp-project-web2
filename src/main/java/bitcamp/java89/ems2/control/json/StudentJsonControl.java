package bitcamp.java89.ems2.control.json;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bitcamp.java89.ems2.domain.Student;
import bitcamp.java89.ems2.service.StudentService;
import bitcamp.java89.ems2.util.MultipartUtil;

@RestController
public class StudentJsonControl {
  @Autowired ServletContext sc;
  @Autowired StudentService studentService;
  
  @RequestMapping("/student/form")
  public String form(Model model) throws Exception {
    model.addAttribute("title", "학생관리-등록폼");
    model.addAttribute("contentPage", "student/form.jsp");
    
    return "main";
  }
  
  
  @RequestMapping("/student/list")
  public AjaxResult list() throws Exception {
    List<Student> list = studentService.getList();
    return new AjaxResult(AjaxResult.SUCCESS, list);
  }
  
  
  @RequestMapping("/student/detail")
  public AjaxResult detail(int memberNo) throws Exception {

    Student student = studentService.getDatail(memberNo);
    
    if (student == null) {
      return new AjaxResult(AjaxResult.FAIL, "해당 아이디의 학생이 없습니다.");
    }
    
    return new AjaxResult(AjaxResult.SUCCESS, student);
  }
  
  
  @RequestMapping("/student/add")
  public AjaxResult add(Student student, MultipartFile photo) throws Exception {

    if (photo != null && photo.getSize() > 0) {
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      student.setPhotoPath(newFilename);
    }
    
    studentService.add(student);
    return new AjaxResult(AjaxResult.SUCCESS, "등록 성공");
  }
  
  
  @RequestMapping("/student/delete")
  public AjaxResult delete(int memberNo) throws Exception {
    
    int count = studentService.delete(memberNo);

    if (count == 0) {
      return new AjaxResult(AjaxResult.FAIL, "해당 번호의 학생이 없습니다.");
    }
    
    return new AjaxResult(AjaxResult.SUCCESS, "삭제 성공");
    
  }
  
  
  @RequestMapping("/student/update")
  public AjaxResult update(Student student, MultipartFile photo) throws Exception {

    if (photo != null && photo.getSize() > 0) {
      String newFilename = MultipartUtil.generateFilename();
      photo.transferTo(new File(sc.getRealPath("/upload/" + newFilename)));
      student.setPhotoPath(newFilename);
    }
    
    int count = studentService.update(student);
    
    if (count == 0) {
      return new AjaxResult(AjaxResult.FAIL, "해당 번호의 학생이 없습니다.");
    }
    
    return new AjaxResult(AjaxResult.SUCCESS, "변경 성공입니다.");
  }
  
}


