package bitcamp.java89.ems2.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MultipartUtil {
  
  static int count = 0;
  
  public static Map<String, String> parse(HttpServletRequest request) throws Exception {
    HashMap<String, String> map = new HashMap<>();
    
    if (!ServletFileUpload.isMultipartContent(request)) {
      throw new Exception("멀티파트 형식이 아닙니다.");
    } else {
      DiskFileItemFactory itemFactory = new DiskFileItemFactory();
      ServletFileUpload uploadParser = new ServletFileUpload(itemFactory);
      List<FileItem> items = uploadParser.parseRequest(request);
      
      for (FileItem item : items) {
        if (item.isFormField()) {
          map.put(item.getFieldName(), item.getString("UTF-8"));
        } else {
          if (item.getSize() == 0) {
            continue;
          }
          
          String filename = System.currentTimeMillis() + "_" + count++;
          
          item.write(new File(request.getServletContext().getRealPath(
              "/upload/" + filename)));
          
          map.put(item.getFieldName(), filename);
        }
      }
      
      return map;
    }
    
    
    
  }
}
