package bitcamp.java89.ems2.control.teacher;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bitcamp.java89.ems2.control.PageController;
import bitcamp.java89.ems2.dao.MemberDao;
import bitcamp.java89.ems2.dao.TeacherDao;
import bitcamp.java89.ems2.domain.Member;
import bitcamp.java89.ems2.domain.Photo;
import bitcamp.java89.ems2.domain.Teacher;
import bitcamp.java89.ems2.util.MultipartUtil;

@Controller("/teacher/add.do")
public class TeacherAddControl implements PageController {
  @Autowired MemberDao memberDao;
  @Autowired TeacherDao teacherDao;
  

  @Override
  public String service(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
}
